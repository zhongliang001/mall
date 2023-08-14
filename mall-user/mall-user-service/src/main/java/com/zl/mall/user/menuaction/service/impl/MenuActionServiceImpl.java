package com.zl.mall.user.menuaction.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.menuaction.entity.MenuActionEntity;
import com.zl.mall.user.menuaction.mapper.MenuActionMapper;
import com.zl.mall.user.menuaction.service.MenuActionService;
/*
 * 
 * @author coolz
 *
*/
@Service
public class MenuActionServiceImpl implements MenuActionService {
	@Autowired
	private MenuActionMapper menuActionMapper;
	public List<MenuActionEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<MenuActionEntity> list = menuActionMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(MenuActionEntity menuActionEntity){
		menuActionEntity.setActionId(UUID.randomUUID().toString().replaceAll("-", ""));
		return menuActionMapper.add(menuActionEntity);
	}
	public int update(MenuActionEntity menuActionEntity){
		return menuActionMapper.update(menuActionEntity);
	}
	public int delete(String actionId){
		return menuActionMapper.delete(actionId);
	}
}