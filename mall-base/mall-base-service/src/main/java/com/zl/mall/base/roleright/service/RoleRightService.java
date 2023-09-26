package com.zl.mall.base.roleright.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.zl.mall.base.roleright.dto.RoleRightConfigDto;
import com.zl.mall.base.roleright.dto.RoleRightDto;
import com.zl.mall.base.roleright.entity.RoleRightEntity;
import com.zl.mall.common.dto.QueryCondition;

/**
 * 
 * @author coolz
 *
 */
@Service
public interface RoleRightService {
	
	/**
	 * 查询角色权限列表
	 * @param queryCondition 查询条件
	 * @return 返回角色列表
	 */
	public List<RoleRightEntity> queryList(QueryCondition queryCondition);

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
	public int delete(String rightId);
	
	/**
	 * 查询权限详细列表
	 * @param queryCondition 查询条件
	 * @return 返回角色列表
	 */
	public List<RoleRightDto> queryDetailList(QueryCondition queryCondition);

	/**
	 * 配置角色权限列表
	 * @param roleRightConfigDto 角色权限配置信息
	 * @return 返回角色列表
	 */
	public int config(RoleRightConfigDto roleRightConfigDto);

	/**
	 * 根据角色id查询角色权限列表
	 * @param roleId 角色Id
	 * @return 返回角色列表
	 */
	public List<String> queryRights(@Param("roleId") String roleId);

}