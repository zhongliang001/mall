package com.zl.mall.base.userrole.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.base.userrole.dto.UserRoleDto;
import com.zl.mall.base.userrole.entity.UserRoleEntity;
import com.zl.mall.common.dto.QueryCondition;
/**
 * 
 * @author coolz
 *
 */
@Service
public interface UserRoleService{
	/**
	 * 查询角色列表
	 * @param queryCondition 查询条件
	 * @return 返回角色列表
	 */
	public List<UserRoleEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增角色
	 * @param userRoleEntity 角色
	 * @return 返回新增记录数
	 */
	public int add(UserRoleEntity userRoleEntity);
	
	/**
	 * 修改角色
	 * @param userRoleEntity 角色
	 * @return 返回修改记录数
	 */
	public int update(UserRoleEntity userRoleEntity);
	
	/**
	 * 删除角色
	 * @param userRoleId 用户角色id
	 * @return 删除角色权限数
	 */
	public int delete(String userRoleId);

	/**
	 * 配置用户角色
	 * @param userRoleDto 用户角色信息
	 * @return 返回配置记录数
	 */
	public int config(UserRoleDto userRoleDto);
}