package com.zl.centric.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zl.centric.user.entity.UserEntity;
/*
 * 
 * @author coolz
 *
*/
public interface UserDao{
	public List<UserEntity> queryList(Map<String, Object> condtion);
	
	public int add(UserEntity userEntity);
	
	public int update(UserEntity userEntity);
	
	public int delete(@Param("userId") String userId );
}