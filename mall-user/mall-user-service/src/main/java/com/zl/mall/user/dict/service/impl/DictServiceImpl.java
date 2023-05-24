package com.zl.mall.user.dict.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.dict.entity.DictEntity;
import com.zl.mall.user.dict.mapper.DictMapper;
import com.zl.mall.user.dict.service.DictService;
/*
 * 
 * @author coolz
 *
*/
@Service
public class DictServiceImpl implements DictService {
	@Autowired
	private DictMapper dictMapper;
	public List<DictEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<DictEntity> list = dictMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(DictEntity dictEntity){
		return dictMapper.add(dictEntity);
	}
	public int update(DictEntity dictEntity){
		return dictMapper.update(dictEntity);
	}
	public int delete(String dictId){
		return dictMapper.delete(dictId);
	}
}