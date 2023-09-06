package com.zl.mall.user.menu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.menu.entity.MenuEntity;
import com.zl.mall.user.menu.mapper.MenuMapper;
import com.zl.mall.user.menu.service.MenuService;
import com.zl.mall.user.menuaction.dto.MenuActionDto;
import com.zl.mall.user.menuaction.entity.MenuActionEntity;
import com.zl.mall.user.menuaction.mapper.MenuActionMapper;
/*
 * 
 * @author coolz
 *
*/
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private MenuActionMapper menuActionMapper;
	
	public List<MenuEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<MenuEntity> list = menuMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(MenuEntity menuEntity){
		menuEntity.setMenuId(UUID.randomUUID().toString().replace("-", ""));
		return menuMapper.add(menuEntity);
	}
	public int update(MenuEntity menuEntity){
		return menuMapper.update(menuEntity);
	}
	public int delete(String menuId){
		return menuMapper.delete(menuId);
	}

	@Override
	public List<Map<String, String>> selectRoot() {
		return menuMapper.selectRoot();
	}
	
	@Override
	public List<MenuActionDto> selectMenuByUserId(Map<String, String> userMap) {	
		String userId = userMap.get("userId");
		if(StringUtils.isEmpty(userId)) {
			return null;
		}
		List<MenuEntity> menus = menuMapper.selectMenuByUserId(userMap);
		Iterator<MenuEntity> it = menus.iterator();
		List<MenuActionDto> list = new ArrayList<MenuActionDto>();
		while (it.hasNext()) {
			MenuActionDto dto = new MenuActionDto();
			MenuEntity next = it.next();
			dto.setMenuCnName(next.getMenuCnName());
			dto.setMenuId(next.getMenuId());
			dto.setPath(next.getPath());
			dto.setMenuName(next.getMenuName());
			dto.setComp(next.getComp());
			// 如果是页面查询权限
			String path = next.getPath();
			if(StringUtils.isNotEmpty(path)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userId", userId);
				map.put("menuId", next.getMenuId());
				List<MenuActionEntity> actions = menuActionMapper.selectByUserId(map);
				if(actions.size()== 0) {
					it.remove();
				}else {
					dto.setList(actions);				
					list.add(dto);
				}
			}else {
				Map<String, String> childMap = new HashMap<String,String>();
				childMap.put("userId", userId);
				childMap.put("parentId", next.getMenuId());
				List<MenuActionDto> children = selectMenuByUserId(childMap);
				if(children.size() == 0) {
					it.remove();
				}else {
					dto.setChildren(children);
					list.add(dto);
				}	
			}
		}
		return list;
	}
}