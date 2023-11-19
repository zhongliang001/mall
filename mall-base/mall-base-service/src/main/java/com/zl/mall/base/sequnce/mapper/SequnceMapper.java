package com.zl.mall.base.sequnce.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.sequnce.entity.SequnceEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface SequnceMapper{
	public List<SequnceEntity> queryList(Map<String, Object> condition);
	
	public int add(SequnceEntity sequnceEntity);
	
	public int update(SequnceEntity sequnceEntity);
	
	public int delete(@Param("seqId") String seqId );
}