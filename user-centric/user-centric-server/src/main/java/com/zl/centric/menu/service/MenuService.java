package com.zl.centric.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.zl.common.dto.QueryCondition;

import com.zl.centric.menu.entity.MenuEntity;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface MenuService{
	
	/**
	 * 菜单列表查询
	 * @param queryCondition 查询条件
	 * @return 返回菜单列表
	 */
	public List<MenuEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增菜单
	 * @param menuEntity 菜单
	 * @return 返回新增记录数
	 */
	public int add(MenuEntity menuEntity);
	
	/**
	 * 修改菜单
	 * @param menuEntity 菜单
	 * @return 返回修改记录数
	 */
	public int update(MenuEntity menuEntity);
	
	/**
	 * 删除菜单
	 * @param menuId 菜单id
	 * @return 返回菜单记录数
	 */
	public int delete(String menuId);
}