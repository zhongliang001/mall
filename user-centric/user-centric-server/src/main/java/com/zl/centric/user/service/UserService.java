package com.zl.centric.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.centric.user.dto.LoginDto;
import com.zl.centric.user.entity.UserEntity;
import com.zl.common.dto.QueryCondition;
import com.zl.common.exception.ZlException;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface UserService{
	/**
	 * 查询用户列表
	 * @param queryCondition 查询条件
	 * @return 用户列表
	 */
	public List<UserEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增用户
	 * @param userEntity 新增用户对象
	 * @return 新增条数
	 */
	public int add(UserEntity userEntity);
	
	/**
	 * 修改用户
	 * @param userEntity 修改用户对象
	 * @return 修改条数
	 */
	public int update(UserEntity userEntity);
	
	/**
	 * 删除用户
	 * @param userId 用户id
	 * @return 删除条数
	 */
	public int delete(String userId);
	
	/**
	 * 登录
	 * @param loginVo 登录对象
	 * @throws ZlException 登录失败异常
	 * @return 返回用户信息
	 */
	public UserEntity login(LoginDto loginDto) throws ZlException;
}