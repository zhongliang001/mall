package com.zl.centric.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.zl.common.dto.QueryCondition;

import com.zl.centric.menu.entity.MenuEntity;
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
}