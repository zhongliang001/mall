package com.zl.centric.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zl.centric.user.entity.UserEntity;
/**
 * 
 * @author coolz
 *
*/
public interface UserMapper{
	/**
	 * 查询用户列表
	 * @param condtion 查询条件
	 * @return 用户列表
	 */
	public List<UserEntity> queryList(Map<String, Object> condtion);
	
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
	public int delete(@Param("userId") String userId );
}