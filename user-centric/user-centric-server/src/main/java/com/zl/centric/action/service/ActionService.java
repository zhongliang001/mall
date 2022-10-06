package com.zl.centric.action.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.centric.action.dto.ActionDto;
import com.zl.centric.action.entity.ActionEntity;
import com.zl.common.dto.QueryCondition;
import com.zl.common.exception.ZlException;

/**
 * 
 * @author coolz
 *
*/
@Service
public interface ActionService{
	
	/**
	 * 查询菜单操作列表
	 * @param queryCondition 查询条件
	 * @return 操作列表
	 */
	public List<ActionEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增菜单操作
	 * @param actionEntity 菜单操作
	 * @return 返回新增记录数
	 */
	public int add(ActionEntity actionEntity) throws ZlException;
	
	/**
	 * 修改菜单操作
	 * @param actionEntity 修改菜单操作
	 * @return 返回修改记录数
	 */
	public int update(ActionEntity actionEntity) throws ZlException;
	
	/**
	 * 删除菜单操作
	 * @param actionId 操作id
	 * @return 返回删除操作id
	 */
	public int delete(String actionId);
	
	/**
	 * 查询菜单操作列表
	 * @param queryCondition 查询条件
	 * @return 菜单操作列表
	 */
	public List<ActionDto> queryMenuAction(QueryCondition queryCondition);
	
	/**
	 * 查询菜单操作详情
	 * @param actionId 操作id
	 * @return 返回菜单操作详情
	 */
	public ActionDto detail(String actionId);
}