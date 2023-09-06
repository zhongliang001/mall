package com.zl.mall.user.userrole.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.use.userrole.dto.UserRoleDto;
import com.zl.mall.use.userrole.entity.UserRoleEntity;
import com.zl.mall.user.userrole.mapper.UserRoleMapper;
import com.zl.mall.user.userrole.service.UserRoleService;
/*
 * 
 * @author coolz
 *
*/
@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleMapper userRoleMapper;
	public List<UserRoleEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<UserRoleEntity> list = userRoleMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(UserRoleEntity userRoleEntity){
		return userRoleMapper.add(userRoleEntity);
	}
	public int update(UserRoleEntity userRoleEntity){
		return userRoleMapper.update(userRoleEntity);
	}
	public int delete(String userRoleId){
		return userRoleMapper.delete(userRoleId);
	}

	@Override
	public int config(UserRoleDto userRoleDto) {
		List<Map<String, String>> list = userRoleDto.getList();
		userRoleMapper.deleteByUserId(userRoleDto.getUserId());
		list.forEach(t->{
			String value = t.get("value");
			UserRoleEntity userRoleEntity = new UserRoleEntity();
			userRoleEntity.setUserRoleId(UUID.randomUUID().toString().replaceAll("-", ""));
			userRoleEntity.setRoleId(value);
			userRoleEntity.setUserId(userRoleDto.getUserId());
			userRoleMapper.add(userRoleEntity);
		});
		
		return list.size();
	}
}