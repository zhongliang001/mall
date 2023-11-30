package com.zl.mall.base.template.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.base.template.entity.TemplateEntity;
import com.zl.mall.common.dto.QueryCondition;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface TemplateService{
	
	/**
	 * 查询模板列表
	 * @param queryCondition 查询条件
	 * @return 返回模板列表
	 */
	public List<TemplateEntity> queryList(QueryCondition queryCondition);
	
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
	public int delete(String id);
	
	/**
	 * 获取流水号
	 * @param name 流水号模板名
	 * @return 返回流水号
	 */
	public String getSeqno(String name);
}