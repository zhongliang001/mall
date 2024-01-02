package com.zl.mall.shopcenter.orderdetail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

/**
 * 
 * @author coolz
 *
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private TemplateClient templateClient;

	@Override
	public List<OrderDetailDto> queryList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
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