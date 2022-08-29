package com.zl.centric.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.centric.user.entity.UserEntity;
import com.zl.centric.user.mapper.UserMapper;
import com.zl.centric.user.service.UserService;
import com.zl.common.dto.QueryCondition;
/*
 * 
 * @author coolz
 *
*/
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userDao;
	public List<UserEntity> queryList(QueryCondition queryCondition){		
		List<UserEntity> list = userDao.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(UserEntity userEntity){
		return userDao.add(userEntity);
	}
	public int update(UserEntity userEntity){
		return userDao.update(userEntity);
	}
	public int delete(String userId){
		return userDao.delete(userId);
	}

	@Override
	public int login() {
		// TODO Auto-generated method stub
		return 0;
	}
}