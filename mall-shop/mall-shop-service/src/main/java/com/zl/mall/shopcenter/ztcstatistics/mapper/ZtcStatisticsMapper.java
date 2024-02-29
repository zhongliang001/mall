package com.zl.mall.shopcenter.ztcstatistics.mapper;

import com.zl.mall.shopcenter.ztcstatistics.entity.ZtcStatisticsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface ZtcStatisticsMapper{
	/**
	 * 查询直通车关键词统计列表
	 * @param condition 查询条件
	 * @return 返回直通车关键词统计列表
	 */
	 List<ZtcStatisticsEntity> queryList(Map<String, Object> condition);
	
	/**
	 * 新增直通车关键词统计
	 * @param ztcStatisticsEntity 直通车关键词统计信息 
	 * @return 新增直通车关键词统计数量
	 */
	int add(ZtcStatisticsEntity ztcStatisticsEntity);
	
	/**
	 * 修改直通车关键词统计
	 * @param ztcStatisticsEntity 直通车关键词统计信息 
	 * @return 修改直通车关键词统计数量
	 */
	int update(ZtcStatisticsEntity ztcStatisticsEntity);
	
	/**
	* 删除直通车关键词统计
	* @param id 直通车关键词统计
	* @return 删除直通车关键词统计数量
	*/
	int delete(@Param("id") String id );

	/**
	 * 批量新增直通车访问数据
	 * @param list 直通车访问数据
	 * @return 新增记录数
	 */
	int insertBatch(List<ZtcStatisticsEntity> list);
}