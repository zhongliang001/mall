package com.zl.mall.user.menuaction.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.menu.entity.MenuEntity;
import com.zl.mall.user.menu.mapper.MenuMapper;
import com.zl.mall.user.menuaction.dto.MenuActionDto;
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

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<MenuActionEntity> queryList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<MenuActionEntity> list = menuActionMapper.queryList(queryCondition.getCondition());
		return list;
	}

	@Override
	public int add(MenuActionEntity menuActionEntity) {
		menuActionEntity.setActionId(UUID.randomUUID().toString().replaceAll("-", ""));
		return menuActionMapper.add(menuActionEntity);
	}

	@Override
	public int update(MenuActionEntity menuActionEntity) {
		return menuActionMapper.update(menuActionEntity);
	}

	@Override
	public int delete(String actionId) {
		return menuActionMapper.delete(actionId);
	}

	@Override
	public List<MenuActionDto> queryAll() {
		List<MenuEntity> roots = menuMapper.queryRoot();
		List<MenuActionDto> list = new ArrayList<>();
		if (roots.size() > 0) {
			for (int i = 0; i < roots.size(); i++) {
				MenuEntity menuEntity = roots.get(i);
				MenuActionDto menuActionDto = generMenuActionDto(menuEntity);
				list.add(menuActionDto);
			}
		}

		return list;
	}

	private MenuActionDto generMenuActionDto(MenuEntity menuEntity) {
		MenuActionDto menuActionDto = new MenuActionDto();
		menuActionDto.setMenuId(menuEntity.getMenuId());
		menuActionDto.setMenuCnName(menuEntity.getMenuCnName());
		Map<String, Object> condition = new HashMap<>();
		condition.put("menuId", menuEntity.getMenuId());
		List<MenuActionEntity> queryList = menuActionMapper.queryList(condition);
		menuActionDto.setList(queryList);
		Map<String, Object> childCondition = new HashMap<>();
		childCondition.put("parentId", menuEntity.getMenuId());
		List<MenuActionDto> list = new ArrayList<>();
		List<MenuEntity> menuEntitys = menuMapper.queryList(childCondition);
		if (menuEntitys.size() > 0) {
			for (int i = 0; i < menuEntitys.size(); i++) {
				MenuActionDto generMenuActionDto = generMenuActionDto(menuEntitys.get(i));
				list.add(generMenuActionDto);
			}
		}
		menuActionDto.setChildren(list);
		return menuActionDto;
	}
}