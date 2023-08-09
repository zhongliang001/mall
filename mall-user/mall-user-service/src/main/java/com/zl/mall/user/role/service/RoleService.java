package com.zl.mall.user.role.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.role.entity.RoleEntity;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface RoleService{
	public List<RoleEntity> queryList(QueryCondition queryCondition);
	
	public int add(RoleEntity roleEntity);
	
	public int update(RoleEntity roleEntity);
	
	public int delete(String roleId);
}