package com.zl.mall.user.userinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.userinfo.entity.UserInfoEntity;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface UserInfoService{
	
	/**
	 * 查询用户信息列表
	 * @param queryCondition 查询条件
	 * @return
	 */
	public List<UserInfoEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增用户信息
	 * @param userInfoEntity 用户授权信息
	 * @return
	 */
	public int add(UserInfoEntity userInfoEntity);
	
	/**
	 * 修改用户信息
	 * @param userInfoEntity 用户授权信息
	 * @return
	 */
	public int update(UserInfoEntity userInfoEntity);
	
	/**
	 * 删除用户信息
	 * @param userId 用户id
	 * @return
	 */
	public int delete(String userId);


	/**
	 * 查询用户信息
	 * @param userId 用户id
	 * @return
	 */
	public UserInfoEntity queryByUserId(String userId);
}