package com.zl.mall.base.roleright.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.roleright.dto.RoleRightDto;
import com.zl.mall.base.roleright.entity.RoleRightEntity;

/**
 * 
 * @author coolz
 *
 */
@Mapper
public interface RoleRightMapper {
	
	/**
	 * 查询角色权限列表
	 * @param condtion 查询条件
	 * @return 返回角色列表
	 */
	public List<RoleRightEntity> queryList(Map<String, Object> condtion);

	/**
	 * 新增角色权限
	 * @param roleRightEntity 角色权限
	 * @return 返回新增记录数
	 */
	public int add(RoleRightEntity roleRightEntity);

	/**
	 * 修改角色权限
	 * @param roleRightEntity 角色权限
	 * @return 返回修改记录数
	 */
	public int update(RoleRightEntity roleRightEntity);

	/**
	 * 删除角色权限
	 * @param rightId 权限id
	 * @return 删除角色权限数
	 */
	public int delete(@Param("rightId") String rightId);

	/**
	 * 查询权限详细列表
	 * @param condition 查询条件
	 * @return 返回角色列表
	 */
	public List<RoleRightDto> queryDetailList(Map<String, Object> condition);

	/**
	 * 根据角色Id删除角色权限
	 * @param roleId 用户角色id
	 * @return 删除角色权限数
	 */
	public int deleteByRoleId(@Param("roleId") String roleId);

	/**
	 * 根据角色id查询角色权限列表
	 * @param roleId 角色Id
	 * @return 返回角色列表
	 */
	public List<String> queryRights(@Param("roleId") String roleId);

}