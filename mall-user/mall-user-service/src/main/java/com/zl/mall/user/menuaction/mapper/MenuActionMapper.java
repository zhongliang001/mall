package com.zl.mall.user.menuaction.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.user.menuaction.entity.MenuActionEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface MenuActionMapper{
	public List<MenuActionEntity> queryList(Map<String, Object> condtion);
	
	public int add(MenuActionEntity menuActionEntity);
	
	public int update(MenuActionEntity menuActionEntity);
	
	public int delete(@Param("actionId") String actionId );
}