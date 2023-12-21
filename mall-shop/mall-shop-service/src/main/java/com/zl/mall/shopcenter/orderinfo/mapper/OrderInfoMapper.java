package com.zl.mall.shopcenter.orderinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.shopcenter.orderinfo.dto.OrderInfoDto;
import com.zl.mall.shopcenter.orderinfo.entity.OrderInfoEntity;
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
	public List<OrderInfoDto> queryList(Map<String, Object> condition);
	
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
	public int delete(@Param("orderId") String orderId );
}