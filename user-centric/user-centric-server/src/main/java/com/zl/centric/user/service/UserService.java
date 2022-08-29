package com.zl.centric.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.centric.user.entity.UserEntity;
import com.zl.common.dto.QueryCondition;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface UserService{
	public List<UserEntity> queryList(QueryCondition queryCondition);
	
	public int add(UserEntity userEntity);
	
	public int update(UserEntity userEntity);
	
	public int delete(String userId);
	
	public int login();
}