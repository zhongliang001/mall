package com.zl.mall.user.userauth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.userauth.dto.UserAuthDto;
import com.zl.mall.user.userauth.dto.UserAuthLogOutDto;
import com.zl.mall.user.userauth.entity.UserAuthEntity;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface UserAuthService{
	
	/**
	 * 查询用户授权信息列表
	 * @param queryCondition 查询条件
	 * @return
	 */
	public List<UserAuthEntity> queryList(QueryCondition queryCondition);
	
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
	public int delete(String userId);
	
	/**
	 * 用户登录
	 * @param userName 用户名
	 * @return
	 */
	public UserAuthEntity login(String userName);
	
	/**
	 * 用户注册
	 * @param userAuthDto 注册用户信息
	 * @return
	 */
	public UserAuthEntity regiter(UserAuthDto userAuthDto);
		
	/**
	 * 用户退出
	 * @param userName 用户名
	 * @return
	 */
	public void logout(UserAuthLogOutDto userAuthLogOutDto);
}