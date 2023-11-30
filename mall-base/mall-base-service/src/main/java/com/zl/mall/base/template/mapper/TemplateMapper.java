package com.zl.mall.base.template.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.template.entity.TemplateEntity;
/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface TemplateMapper{
	
	/**
	 * 查询模板列表
	 * @param condition 查询条件
	 * @return 返回模板列表
	 */
	public List<TemplateEntity> queryList(Map<String, Object> condition);
	
	/**
	 * 新增模板
	 * @param templateEntity 模板对象
	 * @return 新增数量
	 */
	public int add(TemplateEntity templateEntity);
	
	/**
	 * 修改模板
	 * @param templateEntity 模板对象
	 * @return 修改数量
	 */
	public int update(TemplateEntity templateEntity);
	
	/**
	 * 删除模板
	 * @param id 模板id
	 * @return 删除数量
	 */
	public int delete(@Param("id") String id );
}