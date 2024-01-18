package com.zl.mall.shopcenter.areastatistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.shopcenter.areastatistics.entity.AreaStatisticsEntity;
/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface AreaStatisticsMapper{
	/**
	 * 查询订单详细信息列表
	 * @param condition 查询条件
	 * @return 返回订单详细信息列表
		 */
	public List<AreaStatisticsEntity> queryList(Map<String, Object> condition);
	
	/**
	 * 新增订单详细信息
	 * @param areaStatisticsEntity 订单详细信息信息 
	 * @return 新增订单详细信息数量
	 */
	public int add(AreaStatisticsEntity areaStatisticsEntity);
	
	/**
	 * 修改订单详细信息
	 * @param areaStatisticsEntity 订单详细信息信息 
	 * @return 修改订单详细信息数量
	 */
	public int update(AreaStatisticsEntity areaStatisticsEntity);
	
	/**
	* 删除订单详细信息
	* @param id 订单详细信息
	* @return 删除订单详细信息数量
	*/
	public int delete(@Param("id") String id );
}