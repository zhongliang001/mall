package com.zl.centric.menu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zl.centric.menu.entity.MenuEntity;
/**
 * 
 * @author coolz
 *
*/
public interface MenuMapper{
	/**
	 * 菜单列表查询
	 * @param condtion 查询条件
	 * @return 返回菜单列表
	 */
	public List<MenuEntity> queryList(Map<String, Object> condtion);
	
	/**
	 * 新增菜单
	 * @param menuEntity 菜单
	 * @return 返回新增记录数
	 */
	public int add(MenuEntity menuEntity);
	
	/**
	 * 修改菜单
	 * @param menuEntity 菜单
	 * @return 返回修改记录数
	 */
	public int update(MenuEntity menuEntity);
	
	/**
	 * 删除菜单
	 * @param menuId 菜单id
	 * @return 返回菜单记录数
	 */
	public int delete(@Param("menuId") String menuId );
}