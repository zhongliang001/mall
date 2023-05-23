package com.zl.mall.user.userinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.userinfo.entity.UserInfoEntity;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface UserInfoService{
	public List<UserInfoEntity> queryList(QueryCondition queryCondition);
	
	public int add(UserInfoEntity userInfoEntity);
	
	public int update(UserInfoEntity userInfoEntity);
	
	public int delete(String userId);

	public UserInfoEntity queryByUserId(String userId);
}