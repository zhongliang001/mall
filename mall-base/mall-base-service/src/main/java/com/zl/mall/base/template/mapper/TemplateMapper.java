package com.zl.mall.base.template.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.template.entity.TemplateEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface TemplateMapper{
	public List<TemplateEntity> queryList(Map<String, Object> condition);
	
	public int add(TemplateEntity templateEntity);
	
	public int update(TemplateEntity templateEntity);
	
	public int delete(@Param("id") String id );
}