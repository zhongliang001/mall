package com.zl.mall.base.userrole.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.base.userrole.dto.UserRoleDto;
import com.zl.mall.base.userrole.entity.UserRoleEntity;
import com.zl.mall.common.dto.QueryCondition;
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