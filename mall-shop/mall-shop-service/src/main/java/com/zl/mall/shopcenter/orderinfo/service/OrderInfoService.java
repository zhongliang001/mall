package com.zl.mall.shopcenter.orderinfo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.SelectDto;
import com.zl.mall.shopcenter.orderinfo.entity.OrderInfoEntity;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface OrderInfoService{
		/**
	 * 查询订单信息列表
	 * @param queryCondition 查询条件
	 * @return 返回订单信息列表
		 */
	public List<OrderInfoEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增订单信息
	 * @param orderInfoEntity 订单信息信息 
	 * @return 新增订单信息数量
	 */
	public int add(OrderInfoEntity orderInfoEntity);
	
	
	/**
	 * 修改订单信息
	 * @param orderInfoEntity 订单信息信息 
	 * @return 修改订单信息数量
	 */
	public int update(OrderInfoEntity orderInfoEntity);
	
	/**
	 * 删除订单信息
	 * @param orderId 订单信息
	 * @return 删除订单信息数量
	 */
	public int delete(String orderId);
}