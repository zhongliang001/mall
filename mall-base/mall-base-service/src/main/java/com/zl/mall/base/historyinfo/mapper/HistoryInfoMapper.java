package com.zl.mall.base.historyinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.historyinfo.entity.HistoryInfoEntity;
/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface HistoryInfoMapper{
	/**
	 * 查询历史信息列表
	 * @param condition 查询条件
	 * @return 返回历史信息列表
		 */
	public List<HistoryInfoEntity> queryList(Map<String, Object> condition);
	
	/**
	 * 新增历史信息
	 * @param historyInfoEntity 历史信息信息 
	 * @return 新增历史信息数量
	 */
	public int add(HistoryInfoEntity historyInfoEntity);
	
	/**
	 * 修改历史信息
	 * @param historyInfoEntity 历史信息信息 
	 * @return 修改历史信息数量
	 */
	public int update(HistoryInfoEntity historyInfoEntity);
	
	/**
	* 删除历史信息
	* @param id 历史信息
	* @return 删除历史信息数量
	*/
	public int delete(@Param("id") String id );
	
	/**
	 * 批量新增历史信息
	 * @param list 历史信息
	 * @return 新增记录数
	 */
	public int batchAdd(List<HistoryInfoEntity> list);
}