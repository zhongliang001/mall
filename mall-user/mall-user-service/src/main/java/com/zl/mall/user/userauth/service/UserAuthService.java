package com.zl.mall.user.userauth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.userauth.entity.UserAuthEntity;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface UserAuthService{
	public List<UserAuthEntity> queryList(QueryCondition queryCondition);
	
	public int add(UserAuthEntity userAuthEntity);
	
	public int update(UserAuthEntity userAuthEntity);
	
	public int delete(String userId);
	
	public UserAuthEntity login(String userName);
}