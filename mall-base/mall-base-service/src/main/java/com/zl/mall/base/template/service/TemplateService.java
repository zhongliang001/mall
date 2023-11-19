package com.zl.mall.base.template.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.base.template.entity.TemplateEntity;
import com.zl.mall.common.dto.QueryCondition;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface TemplateService{
	public List<TemplateEntity> queryList(QueryCondition queryCondition);
	
	public int add(TemplateEntity templateEntity);
	
	public int update(TemplateEntity templateEntity);
	
	public int delete(String id);
	
	public String getSeqno(TemplateDto templateDto);
}