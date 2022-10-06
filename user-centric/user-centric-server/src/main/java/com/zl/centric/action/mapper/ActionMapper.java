package com.zl.centric.action.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zl.centric.action.dto.ActionDto;
import com.zl.centric.action.entity.ActionEntity;
/**
 * 
 * @author coolz
 *
*/
public interface ActionMapper{
	
	/**
	 * 查询操作列表
	 * @param condtion 查询条件
	 * @return 操作列表
	 */
	public List<ActionEntity> queryList(Map<String, Object> condtion);
	
	/**
	 * 新增菜单操作
	 * @param actionEntity 菜单操作
	 * @return 返回新增记录数
	 */
	public int add(ActionEntity actionEntity);
	
	/**
	 * 修改菜单操作
	 * @param actionEntity 修改菜单操作
	 * @return 返回修改记录数
	 */
	public int update(ActionEntity actionEntity);
	
	/**
	 * 删除菜单操作
	 * @param acitonId 操作id
	 * @return 返回删除操作id
	 */
	public int delete(@Param("actionId") String acitonId );
	
	/**
	 * 查询菜单操作列表
	 * @param condtion 查询条件
	 * @return 菜单操作列表
	 */
	public List<ActionDto> queryMenuAction(Map<String, Object> condtion);
}