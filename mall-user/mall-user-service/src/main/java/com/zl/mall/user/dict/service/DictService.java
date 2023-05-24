package com.zl.mall.user.dict.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.dict.entity.DictEntity;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface DictService{
	public List<DictEntity> queryList(QueryCondition queryCondition);
	
	public int add(DictEntity dictEntity);
	
	public int update(DictEntity dictEntity);
	
	public int delete(String dictId);
}