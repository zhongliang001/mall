package com.zl.mall.base.role.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.role.entity.RoleEntity;

/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface RoleMapper {
	public List<RoleEntity> queryList(Map<String, Object> condtion);

	public int add(RoleEntity roleEntity);

	public int update(RoleEntity roleEntity);

	public int delete(@Param("roleId") String roleId);

	public List<Map<String, String>> queryOwnedRole(@Param("userId") String userId);
	
	public List<Map<String, String>> queryNotOwnedRole(@Param("userId") String userId);
}