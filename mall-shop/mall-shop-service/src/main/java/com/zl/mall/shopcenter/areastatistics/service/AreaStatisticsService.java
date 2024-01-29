package com.zl.mall.shopcenter.areastatistics.service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.shopcenter.areastatistics.entity.AreaStatisticsEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author coolz
 *
*/
@Service
public interface AreaStatisticsService{
		/**
	 * 查询订单详细信息列表
	 * @param queryCondition 查询条件
	 * @return 返回订单详细信息列表
		 */
	public List<AreaStatisticsEntity> queryList(QueryCondition queryCondition);
	
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
	 * @param map 订单详细信息
	 * @return 删除订单详细信息数量
	 */
	public int delete(Map<String, String> map);
}