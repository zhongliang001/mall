package com.zl.mall.base.sequence.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.sequence.entity.SequenceEntity;
/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface SequenceMapper{
	
	/**
	 * 查询序列号
	 * @param condition 查询条件
	 * @return 序列号数组
	 */
	public List<SequenceEntity> queryList(Map<String, Object> condition);
	
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
	public int delete(@Param("seqId") String seqId );
}