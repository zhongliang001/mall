package com.zl.mall.user.menu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.menu.entity.MenuEntity;
import com.zl.mall.user.menu.mapper.MenuMapper;
import com.zl.mall.user.menu.service.MenuService;
/*
 * 
 * @author coolz
 *
*/
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuMapper menuMapper;
	public List<MenuEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<MenuEntity> list = menuMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(MenuEntity menuEntity){
		return menuMapper.add(menuEntity);
	}
	public int update(MenuEntity menuEntity){
		return menuMapper.update(menuEntity);
	}
	public int delete(String menuId){
		return menuMapper.delete(menuId);
	}
}