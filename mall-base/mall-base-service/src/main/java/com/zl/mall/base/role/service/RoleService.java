package com.zl.mall.base.role.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zl.mall.base.role.entity.RoleEntity;
import com.zl.mall.common.dto.QueryCondition;

/*
 * 
 * @author coolz
 *
*/
@Service
public interface RoleService {
	public List<RoleEntity> queryList(QueryCondition queryCondition);

	public int add(RoleEntity roleEntity);

	public int update(RoleEntity roleEntity);

	public int delete(String roleId);

	public List<Map<String, String>> queryOwnedRole(String userId);
	
	public List<Map<String, String>> queryNotOwnedRole(String userId);
}