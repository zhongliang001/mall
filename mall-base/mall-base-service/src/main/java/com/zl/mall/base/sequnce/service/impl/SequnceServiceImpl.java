package com.zl.mall.base.sequnce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.base.sequnce.entity.SequnceEntity;
import com.zl.mall.base.sequnce.mapper.SequnceMapper;
import com.zl.mall.base.sequnce.service.SequnceService;
/*
 * 
 * @author coolz
 *
*/
@Service
public class SequnceServiceImpl implements SequnceService {
	@Autowired
	private SequnceMapper sequnceMapper;
	public List<SequnceEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<SequnceEntity> list = sequnceMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(SequnceEntity sequnceEntity){
		return sequnceMapper.add(sequnceEntity);
	}
	public int update(SequnceEntity sequnceEntity){
		return sequnceMapper.update(sequnceEntity);
	}
	public int delete(String seqId){
		return sequnceMapper.delete(seqId);
	}
}