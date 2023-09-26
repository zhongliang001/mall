package com.zl.mall.base.menu.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zl.mall.base.menu.entity.MenuEntity;
import com.zl.mall.base.menuaction.dto.MenuActionDto;
import com.zl.mall.common.dto.QueryCondition;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface MenuService{
	public List<MenuEntity> queryList(QueryCondition queryCondition);
	
	public int add(MenuEntity menuEntity);
	
	public int update(MenuEntity menuEntity);
	
	public int delete(String menuId);
	
	public List<Map<String, String>> selectRoot();
	
	/**
	 * 根据用户id查询菜单及权限
	 * @param userMap {userId:'', menuId: ''} userId 不许为空
	 * @return
	 */
	public List<MenuActionDto> selectMenuByUserId(Map<String, String> userMap);
}