package com.zl.mall.base.menuaction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.base.menuaction.dto.MenuActionDto;
import com.zl.mall.base.menuaction.entity.MenuActionEntity;
import com.zl.mall.common.dto.QueryCondition;
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