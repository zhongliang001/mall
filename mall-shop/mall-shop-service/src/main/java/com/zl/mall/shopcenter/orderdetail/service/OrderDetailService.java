package com.zl.mall.shopcenter.orderdetail.service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.shopcenter.orderdetail.dto.OrderDetailDto;
import com.zl.mall.shopcenter.orderdetail.entity.OrderDetailEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface OrderDetailService{
		/**
	 * 查询订单详细信息列表
	 * @param queryCondition 查询条件
	 * @return 返回订单详细信息列表
		 */
	public List<OrderDetailDto> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增订单详细信息
	 * @param orderDetailEntity 订单详细信息信息 
	 * @return 新增订单详细信息数量
	 */
	public int add(OrderDetailEntity orderDetailEntity);
	
	
	/**
	 * 修改订单详细信息
	 * @param orderDetailEntity 订单详细信息信息 
	 * @return 修改订单详细信息数量
	 */
	public int update(OrderDetailEntity orderDetailEntity);
	
	/**
	 * 删除订单详细信息
	 * @param map 订单详细信息
	 * @return 删除订单详细信息数量
	 */
	public int delete(Map<String, String> map);
	
	/**
	* 依据订单编号删除订单详细信息
	* @param map 订单详细信息
	* @return 删除订单详细信息数量
	*/
	public int deleteByOrderId(Map<String, String> map);

}