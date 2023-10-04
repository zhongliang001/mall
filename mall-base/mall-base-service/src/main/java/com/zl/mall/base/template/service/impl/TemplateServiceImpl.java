package com.zl.mall.base.template.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.base.template.entity.TemplateEntity;
import com.zl.mall.base.template.mapper.TemplateMapper;
import com.zl.mall.base.template.service.TemplateService;
/*
 * 
 * @author coolz
 *
*/
@Service
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private TemplateMapper templateMapper;
	public List<TemplateEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<TemplateEntity> list = templateMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(TemplateEntity templateEntity){
		String id = templateEntity.getId();
		if(StringUtils.isEmpty(id)) {
			templateEntity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		return templateMapper.add(templateEntity);
	}
	public int update(TemplateEntity templateEntity){
		return templateMapper.update(templateEntity);
	}
	public int delete(String id){
		return templateMapper.delete(id);
	}
}