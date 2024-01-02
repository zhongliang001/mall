package com.zl.mall.shopcenter.orderinfo.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

/**
 * 
 * @author coolz
 *
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
	@Autowired
	private OrderInfoMapper orderInfoMapper;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private TemplateClient templateClient;

	@Override
	public List<OrderInfoEntity> queryList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<OrderInfoEntity> list = orderInfoMapper.queryList(queryCondition.getCondition());
		return list;
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
	public int delete(String orderId) {
		return orderInfoMapper.delete(orderId);
	}

	@Override
	public int addOrder(OrderDto orderDto) {
		OrderInfoEntity orderInfo = orderDto.getOrderInfo();
		int num = 0;
		num = this.add(orderInfo);
		String orderId = orderInfo.getOrderId();
		List<OrderDetailEntity> list = orderDto.getOrderDetails();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				OrderDetailEntity orderDetailEntity = list.get(i);
				orderDetailEntity.setOrderId(orderId);
				num += orderDetailService.add(orderDetailEntity);
			}
		}
		return num;
	}

	@Override
	public int updateOrder(OrderDto orderDto) {
		OrderInfoEntity orderInfo = orderDto.getOrderInfo();
		int num = 0;
		num = orderInfoMapper.update(orderInfo);
		String orderId = orderInfo.getOrderId();
		List<OrderDetailEntity> orderDetails = orderDto.getOrderDetails();
		if (orderDetails != null) {
			for (int i = 0; i < orderDetails.size(); i++) {
				OrderDetailEntity orderDetailEntity = orderDetails.get(i);
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
	public int deleteOrder(String orderId) {
		int num = 0;
		num = orderInfoMapper.delete(orderId);
		num += orderDetailService.deleteByOrderId(orderId);

		return num;
	}
}