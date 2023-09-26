package com.zl.mall.base.userrole.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.userrole.entity.UserRoleEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface UserRoleMapper{
	public List<UserRoleEntity> queryList(Map<String, Object> condition);
	
	public int add(UserRoleEntity userRoleEntity);
	
	public int update(UserRoleEntity userRoleEntity);
	
	public int delete(@Param("userRoleId") String userRoleId );
	
	public int  deleteByUserId(@Param("userId") String userId );
}