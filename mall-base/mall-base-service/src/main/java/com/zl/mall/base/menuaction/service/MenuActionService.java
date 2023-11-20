package com.zl.mall.base.menuaction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.base.menuaction.dto.MenuActionDto;
import com.zl.mall.base.menuaction.entity.MenuActionEntity;
import com.zl.mall.common.dto.QueryCondition;
/**
 * 
 * @author coolz
 *
 */
@Service
public interface MenuActionService{
	
	/**
	 * 查询菜单操作列表
	 * @param condition 查询条件
	 * @return 返回菜单列表
	 */
	public List<MenuActionEntity> queryList(QueryCondition condition);
	
	/**
	 * 新增菜单操作
	 * @param menuActionEntity 菜单操作
	 * @return 返回新增记录数
	 */
	public int add(MenuActionEntity menuActionEntity);
	
	/**
	 * 修改菜单操作
	 * @param menuActionEntity 菜单操作
	 * @return 返回修改记录数
	 */
	public int update(MenuActionEntity menuActionEntity);
	
	/**
	 * 删除菜单操作
	 * @param actionId 菜单操作id
	 * @return 返回删除记录数
 	 */
	public int delete(String actionId);
	
	/**
	 * 查询所有的菜单操作
	 * @return 返回所有操作
	 */
	public List<MenuActionDto> queryAll();
}