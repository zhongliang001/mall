package com.zl.mall.base.role.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.role.entity.RoleEntity;

/**
 * 
 * @author coolz
 *
 */
@Mapper
public interface RoleMapper {
	/**
	 * 查询角色列表
	 * @param condition 查询条件
	 * @return 返回角色列表
	 */
	public List<RoleEntity> queryList(Map<String, Object> condition);

	/**
	 * 新增角色
	 * @param roleEntity 角色
	 * @return 返回新增记录数
	 */
	public int add(RoleEntity roleEntity);

	/**
	 * 修改角色
	 * @param roleEntity 角色
	 * @return 返回修改记录数
	 */
	public int update(RoleEntity roleEntity);

	/**
	 * 删除角色
	 * @param roleId 角色id
	 * @return 删除角色数
	 */
	public int delete(@Param("roleId") String roleId);

	/**
	 * 根据用户id查询已经拥有角色
	 * @param userId 角色id
	 * @return 返回用户已经拥有的角色信息
	 */
	public List<Map<String, String>> queryOwnedRole(@Param("userId") String userId);
	
	/**
	 * 根据用户id查询未拥有角色
	 * @param userId 角色id
	 * @return 返回用户未拥有的角色信息
	 */
	public List<Map<String, String>> queryNotOwnedRole(@Param("userId") String userId);
}