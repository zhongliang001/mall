package com.zl.mall.shopcenter.orderinfo.service.impl;

import java.util.List;

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
import com.zl.mall.shopcenter.orderinfo.dto.OrderInfoDto;
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
	private TemplateClient templateClient;

	@Override
	public List<OrderInfoDto> queryList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<OrderInfoDto> list = orderInfoMapper.queryList(queryCondition.getCondition());
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
		if(requestUser != null) {
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
}