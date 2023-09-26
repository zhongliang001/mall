package com.zl.mall.base.menuaction.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.menuaction.entity.MenuActionEntity;
/**
 * 
 * @author coolz
 *
 */
@Mapper
public interface MenuActionMapper{
	
	/**
	 * 查询菜单操作列表
	 * @param condition 查询条件
	 * @return 返回菜单列表
	 */
	public List<MenuActionEntity> queryList(Map<String, Object> condition);
	
	/**
	 * 新增菜单操作
	 * @param menuActionEntity 菜单操作
	 * @return 返回新增记录数
	 */
	public int add(MenuActionEntity menuActionEntity);
	
	/**
	 * 修改菜单操作
	 * @param menuActionEntity 菜单操作
	 * @return 返回修改记录数
	 */
	public int update(MenuActionEntity menuActionEntity);
	
	/**
	 * 删除菜单操作
	 * @param actionId 菜单操作id
	 * @return 返回删除记录数
 	 */
	public int delete(@Param("actionId") String actionId );
	
	/**
	 * 根据用户id 查询菜单操作
	 * @param map 用户id
	 * @return 返回菜单操作列表
	 */
	public List<MenuActionEntity> selectByUserId(Map<String, String> map);
}