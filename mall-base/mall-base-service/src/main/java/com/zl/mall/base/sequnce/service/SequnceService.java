package com.zl.mall.base.sequnce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.zl.mall.common.dto.QueryCondition;

import com.zl.mall.base.sequnce.entity.SequnceEntity;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface SequnceService{
	public List<SequnceEntity> queryList(QueryCondition queryCondition);
	
	public int add(SequnceEntity sequnceEntity);
	
	public int update(SequnceEntity sequnceEntity);
	
	public int delete(String seqId);
}