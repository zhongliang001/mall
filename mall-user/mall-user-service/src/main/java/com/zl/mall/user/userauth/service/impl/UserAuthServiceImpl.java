package com.zl.mall.user.userauth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.userauth.entity.UserAuthEntity;
import com.zl.mall.user.userauth.mapper.UserAuthMapper;
import com.zl.mall.user.userauth.service.UserAuthService;
/*
 * 
 * @author coolz
 *
*/
@Service
public class UserAuthServiceImpl implements UserAuthService {
	@Autowired
	private UserAuthMapper userAuthMapper;
	public List<UserAuthEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<UserAuthEntity> list = userAuthMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(UserAuthEntity userAuthEntity){
		return userAuthMapper.add(userAuthEntity);
	}
	public int update(UserAuthEntity userAuthEntity){
		return userAuthMapper.update(userAuthEntity);
	}
	public int delete(String userId){
		return userAuthMapper.delete(userId);
	}

	@Override
	public UserAuthEntity login(String userName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		List<UserAuthEntity> queryList = userAuthMapper.queryList(map);		
		return queryList.get(0);
	}
}