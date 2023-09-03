package com.zl.mall.user.userrole.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.use.userrole.dto.UserRoleDto;
import com.zl.mall.use.userrole.entity.UserRoleEntity;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface UserRoleService{
	public List<UserRoleEntity> queryList(QueryCondition queryCondition);
	
	public int add(UserRoleEntity userRoleEntity);
	
	public int update(UserRoleEntity userRoleEntity);
	
	public int delete(String userRoleId);

	public int config(UserRoleDto userRoleDto);
}