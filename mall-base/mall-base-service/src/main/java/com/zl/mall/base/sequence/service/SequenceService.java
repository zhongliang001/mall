package com.zl.mall.base.sequence.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.base.sequence.entity.SequenceEntity;
import com.zl.mall.common.dto.QueryCondition;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface SequenceService{
	
	/**
	 * 查询序列号
	 * @param queryCondition 查询条件
	 * @return 序列号数组
	 */
	public List<SequenceEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增序列号
	 * @param sequenceEntity 序列号对象
	 * @return 新增数量
	 */
	public int add(SequenceEntity sequenceEntity);
	
	/**
	 * 修改序列号
	 * @param sequenceEntity 序列号对象
	 * @return 修改数量
	 */
	public int update(SequenceEntity sequenceEntity);
	
	/**
	 * 删除序列号
	 * @param seqId 序列号id
	 * @return 删除数量
	 */
	public int delete(String seqId);
	
	/**
	 * 获取序列号
	 * @param seqName 序列号
	 * @return 返回序列号
	 */
	public String getSequence(String seqName);
}