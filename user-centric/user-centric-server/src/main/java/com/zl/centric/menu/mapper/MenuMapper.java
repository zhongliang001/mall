package com.zl.centric.menu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zl.centric.menu.entity.MenuEntity;
/*
 * 
 * @author coolz
 *
*/
public interface MenuMapper{
	public List<MenuEntity> queryList(Map<String, Object> condtion);
	
	public int add(MenuEntity menuEntity);
	
	public int update(MenuEntity menuEntity);
	
	public int delete(@Param("menuId") String menuId );
}