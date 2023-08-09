package com.zl.mall.user.role.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.role.entity.RoleEntity;
import com.zl.mall.user.role.mapper.RoleMapper;
import com.zl.mall.user.role.service.RoleService;
/*
 * 
 * @author coolz
 *
*/
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	public List<RoleEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<RoleEntity> list = roleMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(RoleEntity roleEntity){
		roleEntity.setRoleId(UUID.randomUUID().toString().replaceAll("-", ""));
		return roleMapper.add(roleEntity);
	}
	public int update(RoleEntity roleEntity){
		return roleMapper.update(roleEntity);
	}
	public int delete(String roleId){
		return roleMapper.delete(roleId);
	}
}