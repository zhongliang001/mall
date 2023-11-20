package com.zl.mall.base.sequence.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.sequence.entity.SequenceEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface SequenceMapper{
	public List<SequenceEntity> queryList(Map<String, Object> condition);
	
	public int add(SequenceEntity sequenceEntity);
	
	public int update(SequenceEntity sequenceEntity);
	
	public int delete(@Param("seqId") String seqId );
}