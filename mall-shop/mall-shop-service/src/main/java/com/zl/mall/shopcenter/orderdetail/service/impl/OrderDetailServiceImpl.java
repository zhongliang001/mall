package com.zl.mall.shopcenter.orderdetail.service.impl;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.template.TemplateClient;
import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.shopcenter.orderdetail.dto.OrderDetailDto;
import com.zl.mall.shopcenter.orderdetail.entity.OrderDetailEntity;
import com.zl.mall.shopcenter.orderdetail.mapper.OrderDetailMapper;
import com.zl.mall.shopcenter.orderdetail.service.OrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.List;

/**
 * 
 * @author coolz
 *
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	private final OrderDetailMapper orderDetailMapper;
	private final TemplateClient templateClient;

	@Inject
	public OrderDetailServiceImpl(OrderDetailMapper orderDetailMapper, TemplateClient templateClient) {
		Assert.notNull(orderDetailMapper, "orderDetailMapper must not be null!");
		Assert.notNull(templateClient, "templateClient must not be null!");
		this.orderDetailMapper = orderDetailMapper;
		this.templateClient = templateClient;
	}

	@Override
	public List<OrderDetailDto> queryList(QueryCondition queryCondition) {
		int size = queryCondition.getPageSize();
		if (size==0) {
			PageHelper.clearPage();
		}else{
			PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		}
		List<OrderDetailDto> list = orderDetailMapper.queryList(queryCondition.getCondition());
		return list;
	}

	@Override
	public int add(OrderDetailEntity orderDetailEntity) {
		TemplateDto templateDto = new TemplateDto();
		templateDto.setName(TempConstant.ORDER_DETAIL_TEMP);
		ResultDto<String> seqno = templateClient.getSeqno(templateDto);
		orderDetailEntity.setOrderDetailId(seqno.getData());
		return orderDetailMapper.add(orderDetailEntity);
	}

	@Override
	public int update(OrderDetailEntity orderDetailEntity) {
		return orderDetailMapper.update(orderDetailEntity);
	}

	@Override
	public int delete(String orderDetailId) {
		return orderDetailMapper.delete(orderDetailId);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return orderDetailMapper.deleteByOrderId(orderId);
	}

}