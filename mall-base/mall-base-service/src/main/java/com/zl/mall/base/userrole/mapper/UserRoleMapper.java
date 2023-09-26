package com.zl.mall.base.userrole.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.userrole.entity.UserRoleEntity;
/**
 * 
 * @author coolz
 *
 */
@Mapper
public interface UserRoleMapper{
	
	/**
	 * 查询角色列表
	 * @param condition 查询条件
	 * @return 返回角色列表
	 */
	public List<UserRoleEntity> queryList(Map<String, Object> condition);
	
	/**
	 * 新增角色
	 * @param userRoleEntity 角色
	 * @return 返回新增记录数
	 */
	public int add(UserRoleEntity userRoleEntity);
	
	/**
	 * 修改用户角色
	 * @param userRoleEntity 用户角色
	 * @return 返回修改记录数
	 */
	public int update(UserRoleEntity userRoleEntity);
	
	/**
	 * 删除用户角色
	 * @param userRoleId 用户角色id
	 * @return 删除角色权限数
	 */
	public int delete(@Param("userRoleId") String userRoleId );
	
	/**
	 * 根据用户id删除角色
	 * @param userId 用户id
	 * @return 删除角色权限数
	 */
	public int  deleteByUserId(@Param("userId") String userId );
}