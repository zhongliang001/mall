package com.zl.mall.shopcenter.orderinfo.service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.shopcenter.orderinfo.dto.OrderDto;
import com.zl.mall.shopcenter.orderinfo.entity.OrderInfoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
	List<OrderInfoEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增订单信息
	 * @param orderInfoEntity 订单信息信息 
	 * @return 新增订单信息数量
	 */
	int add(OrderInfoEntity orderInfoEntity);
	
	
	/**
	 * 修改订单信息
	 * @param orderInfoEntity 订单信息信息 
	 * @return 修改订单信息数量
	 */
	int update(OrderInfoEntity orderInfoEntity);
	
	/**
	 * 删除订单信息
	 * @param map 订单信息
	 * @return 删除订单信息数量
	 */
	int delete(Map<String, String> map);

	/**
	 * 新增订单
	 * @param orderDto 订单详情
	 * @return 新增记录数
	 */
	int addOrder(OrderDto orderDto);

	/**
	 * 修改订单
	 * @param orderDto 订单详情
	 * @return 修改记录数
	 */
	int updateOrder(OrderDto orderDto);

	/**
	 * 删除订单信息和订单详情
	 * @param map 订单信息
	 * @return 删除订单信息数量
	 */
	int deleteOrder(Map<String, String> map);
}