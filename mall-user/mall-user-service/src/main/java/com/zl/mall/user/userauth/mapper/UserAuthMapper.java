package com.zl.mall.user.userauth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.user.userauth.entity.UserAuthEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface UserAuthMapper{
	
	/**
	 * 查询用户授权信息列表
	 * @param condtion 查询条件
	 * @return
	 */
	public List<UserAuthEntity> queryList(Map<String, Object> condtion);
	
	/**
	 * 新增用户授权信息
	 * @param userAuthEntity 用户授权信息
	 * @return
	 */
	public int add(UserAuthEntity userAuthEntity);
	
	/**
	 * 修改用户授权信息
	 * @param userAuthEntity 用户授权信息
	 * @return
	 */
	public int update(UserAuthEntity userAuthEntity);
	
	/**
	 * 删除用户授权信息
	 * @param userId 用户id
	 * @return
	 */
	public int delete(@Param("userId") String userId );
}