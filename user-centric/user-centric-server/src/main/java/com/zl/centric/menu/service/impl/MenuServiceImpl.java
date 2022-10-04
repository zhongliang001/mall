package com.zl.centric.menu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.centric.menu.entity.MenuEntity;
import com.zl.centric.menu.mapper.MenuMapper;
import com.zl.centric.menu.service.MenuService;
import com.zl.common.dto.QueryCondition;
/**
 * 
 * @author coolz
 *
*/
@Service
public class MenuServiceImpl implements MenuService {
	
	private Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Autowired
	private MenuMapper menuMapper;
	
	public List<MenuEntity> queryList(QueryCondition queryCondition){		
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
	
	public boolean checkParentId(String menuId){	
		logger.info("查询菜单id:{}是否存在...", menuId);
		Map<String, Object> map = new HashMap<String,Object>(16);
		map.put("menuId", menuId);
		List<MenuEntity> list = menuMapper.queryList(map);
		logger.info("查询菜单id:{}是否存在的结果为：{}", menuId,list.size() != 0 ? '是': '否');
		return list.size() != 0;
	}
}