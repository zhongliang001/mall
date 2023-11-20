package com.zl.mall.base.sequence.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.base.sequence.entity.SequenceEntity;
import com.zl.mall.common.dto.QueryCondition;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface SequenceService{
	public List<SequenceEntity> queryList(QueryCondition queryCondition);
	
	public int add(SequenceEntity sequenceEntity);
	
	public int update(SequenceEntity sequenceEntity);
	
	public int delete(String seqId);
	
	public String getSequence(String seqName);
}