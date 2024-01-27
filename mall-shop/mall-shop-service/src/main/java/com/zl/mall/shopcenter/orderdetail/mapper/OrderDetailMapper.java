package com.zl.mall.shopcenter.orderdetail.mapper;

import com.zl.mall.shopcenter.orderdetail.dto.OrderDetailDto;
import com.zl.mall.shopcenter.orderdetail.entity.OrderDetailEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface OrderDetailMapper{
	/**
	 * 查询订单详细信息列表
	 * @param condition 查询条件
	 * @return 返回订单详细信息列表
		 */
	List<OrderDetailDto> queryList(Map<String, Object> condition);
	
	/**
	 * 新增订单详细信息
	 * @param orderDetailEntity 订单详细信息信息 
	 * @return 新增订单详细信息数量
	 */
	int add(OrderDetailEntity orderDetailEntity);
	
	/**
	 * 修改订单详细信息
	 * @param orderDetailEntity 订单详细信息信息 
	 * @return 修改订单详细信息数量
	 */
	int update(OrderDetailEntity orderDetailEntity);
	
	/**
	* 删除订单详细信息
	* @param map 订单详细信息
	* @return 删除订单详细信息数量
	*/
	int delete(Map<String, String> map);
	
	/**
	* 依据订单编号删除订单详细信息
	* @param map 订单详细信息
	* @return 删除订单详细信息数量
	*/
	int deleteByOrderId(Map<String, String> map);
}