package com.zl.mall.shopcenter.orderinfo.service.impl;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.template.TemplateClient;
import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.utils.DateUtils;
import com.zl.mall.common.utils.HttpRequestUtil;
import com.zl.mall.shopcenter.orderdetail.entity.OrderDetailEntity;
import com.zl.mall.shopcenter.orderdetail.service.OrderDetailService;
import com.zl.mall.shopcenter.orderinfo.dto.OrderDto;
import com.zl.mall.shopcenter.orderinfo.entity.OrderInfoEntity;
import com.zl.mall.shopcenter.orderinfo.mapper.OrderInfoMapper;
import com.zl.mall.shopcenter.orderinfo.service.OrderInfoService;
import com.zl.mall.user.userauth.entity.UserAuthEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * @author coolz
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    private final OrderInfoMapper orderInfoMapper;
    private final OrderDetailService orderDetailService;
    private final TemplateClient templateClient;

    @Inject
    public OrderInfoServiceImpl(OrderInfoMapper orderInfoMapper, OrderDetailService orderDetailService, TemplateClient templateClient) {
        Assert.notNull(orderDetailService, "OrderInfoMapper must not be null!");
        Assert.notNull(orderInfoMapper, "OrderDetailService must not be null!");
        Assert.notNull(templateClient, "TemplateClient must not be null!");
        this.orderDetailService = orderDetailService;
        this.orderInfoMapper = orderInfoMapper;
        this.templateClient = templateClient;
    }

    @Override
    public List<OrderInfoEntity> queryList(QueryCondition queryCondition) {
        PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
        return orderInfoMapper.queryList(queryCondition.getCondition());
    }

    @Override
    public int add(OrderInfoEntity orderInfoEntity) {
        TemplateDto templateDto = new TemplateDto();
        templateDto.setName(TempConstant.ORDER_TEMP);
        ResultDto<String> seqno = templateClient.getSeqno(templateDto);
        orderInfoEntity.setOrderId(seqno.getData());
        orderInfoEntity.setCreateDate(DateUtils.getCurrentDate());
        UserAuthEntity requestUser = HttpRequestUtil.getRequestUser();
        if (requestUser != null) {
            String userId = requestUser.getUserId();
            orderInfoEntity.setCreatedUserId(userId);
        }
        return orderInfoMapper.add(orderInfoEntity);
    }

    @Override
    public int update(OrderInfoEntity orderInfoEntity) {
        return orderInfoMapper.update(orderInfoEntity);
    }

    @Override
    public int delete(Map<String, String> map) {
        return orderInfoMapper.delete(map);
    }

    @Override
    public int addOrder(OrderDto orderDto) {
        OrderInfoEntity orderInfo = orderDto.getOrderInfo();
        int num = this.add(orderInfo);
        String orderId = orderInfo.getOrderId();
        String shopId= orderInfo.getShopId();
        List<OrderDetailEntity> list = orderDto.getOrderDetails();
        if (list != null) {
            for (OrderDetailEntity orderDetailEntity : list) {
                orderDetailEntity.setOrderId(orderId);
                orderDetailEntity.setShopId(shopId);
                num += orderDetailService.add(orderDetailEntity);
            }
        }
        return num;
    }

    @Override
    public int updateOrder(OrderDto orderDto) {
        OrderInfoEntity orderInfo = orderDto.getOrderInfo();
        int num = orderInfoMapper.update(orderInfo);
        String orderId = orderInfo.getOrderId();
        List<OrderDetailEntity> orderDetails = orderDto.getOrderDetails();
        if (orderDetails != null) {
            for (OrderDetailEntity orderDetailEntity : orderDetails) {
                String orderDetailId = orderDetailEntity.getOrderDetailId();
                if (StringUtils.isNotEmpty(orderDetailId)) {
                    num += orderDetailService.update(orderDetailEntity);
                } else {
                    orderDetailEntity.setOrderId(orderId);
                    num += orderDetailService.add(orderDetailEntity);
                }
            }
        }

        return num;
    }

    @Override
    public int deleteOrder(Map<String, String> map) {
        int num = orderInfoMapper.delete(map);
        String orderId = map.get("orderId");
        if (StringUtils.isNotEmpty(orderId)) {
            num += orderDetailService.deleteByOrderId(map);
        }
        return num;
    }
}