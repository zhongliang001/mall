package com.zl.mall.base.menu.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zl.mall.base.menu.entity.MenuEntity;
import com.zl.mall.base.menuaction.dto.MenuActionDto;
import com.zl.mall.common.dto.QueryCondition;
/**
 * 
 * @author coolz
 *
 */
@Service
public interface MenuService{
	
	/**
	 * 查询菜单列表
	 * @param condition 查询条件
	 * @return 返回菜单列表
	 */
	public List<MenuEntity> queryList(QueryCondition condition);
	
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
	 * @return 返回删除记录数
 	 */
	public int delete(String menuId);
	
	/**
	 * 查询根菜单
	 * @return 返回跟菜单列表
	 */
	public List<Map<String, String>> selectRoot();
	
	/**
	 * 根据用户id查询菜单及权限
	 * @param userMap {userId:'', menuId: ''} userId 不许为空
	 * @return
	 */
	public List<MenuActionDto> selectMenuByUserId(Map<String, String> userMap);
}