package com.zl.mall.shopcenter.orderinfo.mapper;

import com.zl.mall.shopcenter.orderinfo.entity.OrderInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface OrderInfoMapper{
	/**
	 * 查询订单信息列表
	 * @param condition 查询条件
	 * @return 返回订单信息列表
		 */
	 List<OrderInfoEntity> queryList(Map<String, Object> condition);
	
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
	 int delete(Map<String, String> map );
}