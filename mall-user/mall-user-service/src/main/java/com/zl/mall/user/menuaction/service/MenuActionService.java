package com.zl.mall.user.menuaction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.menuaction.dto.MenuActionDto;
import com.zl.mall.user.menuaction.entity.MenuActionEntity;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface MenuActionService{
	public List<MenuActionEntity> queryList(QueryCondition queryCondition);
	
	public int add(MenuActionEntity menuActionEntity);
	
	public int update(MenuActionEntity menuActionEntity);
	
	public int delete(String actionId);
	
	public List<MenuActionDto> queryAll();
}