package com.zl.mall.user.userinfo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.userinfo.entity.UserInfoEntity;
import com.zl.mall.user.userinfo.mapper.UserInfoMapper;
import com.zl.mall.user.userinfo.service.UserInfoService;
/*
 * 
 * @author coolz
 *
*/
@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoMapper userInfoMapper;
	public List<UserInfoEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<UserInfoEntity> list = userInfoMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(UserInfoEntity userInfoEntity){
		return userInfoMapper.add(userInfoEntity);
	}
	public int update(UserInfoEntity userInfoEntity){
		return userInfoMapper.update(userInfoEntity);
	}
	public int delete(String userId){
		return userInfoMapper.delete(userId);
	}

	@Override
	public UserInfoEntity queryByUserId(String userId) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		List<UserInfoEntity> list = userInfoMapper.queryList(map);
		return list.get(0);
	}
}