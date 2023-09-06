package com.zl.mall.user.roleright.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.user.roleright.dto.RoleRightDto;
import com.zl.mall.user.roleright.entity.RoleRightEntity;

/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface RoleRightMapper {
	public List<RoleRightEntity> queryList(Map<String, Object> condtion);

	public int add(RoleRightEntity roleRightEntity);

	public int update(RoleRightEntity roleRightEntity);

	public int delete(@Param("rightId") String rightId);

	public List<RoleRightDto> queryDetailList(Map<String, Object> condition);

	public int deleteByRoleId(@Param("roleId") String roleId);

	public List<String> queryRights(@Param("roleId") String roleId);

}