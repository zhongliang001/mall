package com.zl.mall.base.historyinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.base.historyinfo.dto.HistoryInfoDto;
import com.zl.mall.base.historyinfo.entity.HistoryInfoEntity;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface HistoryInfoService{
		/**
	 * 查询历史信息列表
	 * @param queryCondition 查询条件
	 * @return 返回历史信息列表
		 */
	public List<HistoryInfoEntity> queryList(QueryCondition queryCondition);
	
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
	public int delete(String id);

	/**
	 * 批量新增历史信息
	 * @param historyInfoDto 历史信息
	 * @return 新增记录数
	 */
	public int batchAdd(HistoryInfoDto historyInfoDto);
}