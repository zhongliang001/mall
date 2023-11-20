package com.zl.mall.base.menu.service.impl;

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
import com.zl.mall.base.menu.entity.MenuEntity;
import com.zl.mall.base.menu.mapper.MenuMapper;
import com.zl.mall.base.menu.service.MenuService;
import com.zl.mall.base.menuaction.dto.MenuActionDto;
import com.zl.mall.base.menuaction.entity.MenuActionEntity;
import com.zl.mall.base.menuaction.mapper.MenuActionMapper;
import com.zl.mall.base.template.service.TemplateService;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;

/**
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

	@Autowired
	private TemplateService templateService;

	@Override
	public List<MenuEntity> queryList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<MenuEntity> list = menuMapper.queryList(queryCondition.getCondition());
		return list;
	}

	@Override
	public int add(MenuEntity menuEntity) {
		String seqno = templateService.getSeqno(TempConstant.MENU_TEMP);
		if (StringUtils.isNotEmpty(seqno)) {
			menuEntity.setMenuId(seqno);
		} else {
			menuEntity.setMenuId(UUID.randomUUID().toString().replace("-", ""));
		}
		return menuMapper.add(menuEntity);
	}

	@Override
	public int update(MenuEntity menuEntity) {
		return menuMapper.update(menuEntity);
	}

	@Override
	public int delete(String menuId) {
		return menuMapper.delete(menuId);
	}

	@Override
	public List<Map<String, String>> selectRoot() {
		return menuMapper.selectRoot();
	}

	@Override
	public List<MenuActionDto> selectMenuByUserId(Map<String, String> userMap) {
		String userId = userMap.get("userId");
		if (StringUtils.isEmpty(userId)) {
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
			if (StringUtils.isNotEmpty(path)) {
				Map<String, String> map = new HashMap<String, String>(16);
				map.put("userId", userId);
				map.put("menuId", next.getMenuId());
				List<MenuActionEntity> actions = menuActionMapper.selectByUserId(map);
				if (actions.size() == 0) {
					it.remove();
				} else {
					dto.setList(actions);
					list.add(dto);
				}
			} else {
				Map<String, String> childMap = new HashMap<String, String>(16);
				childMap.put("userId", userId);
				childMap.put("parentId", next.getMenuId());
				List<MenuActionDto> children = selectMenuByUserId(childMap);
				if (children.size() == 0) {
					it.remove();
				} else {
					dto.setChildren(children);
					list.add(dto);
				}
			}
		}
		return list;
	}
}