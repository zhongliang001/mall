package com.zl.mall.base.roleright.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.roleright.dto.RoleRightConfigDto;
import com.zl.mall.base.roleright.dto.RoleRightDto;
import com.zl.mall.base.roleright.entity.RoleRightEntity;
import com.zl.mall.base.roleright.mapper.RoleRightMapper;
import com.zl.mall.base.roleright.service.RoleRightService;
import com.zl.mall.common.dto.QueryCondition;
/**
 * 
 * @author coolz
 *
 */
@Service
public class RoleRightServiceImpl implements RoleRightService {
	@Autowired
	private RoleRightMapper roleRightMapper;
	public List<RoleRightEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<RoleRightEntity> list = roleRightMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	@Override
	public int add(RoleRightEntity roleRightEntity){
		return roleRightMapper.add(roleRightEntity);
	}
	
	@Override
	public int update(RoleRightEntity roleRightEntity){
		return roleRightMapper.update(roleRightEntity);
	}
	
	@Override
	public int delete(String rightId){
		return roleRightMapper.delete(rightId);
	}

	@Override
	public List<RoleRightDto> queryDetailList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<RoleRightDto> list = roleRightMapper.queryDetailList(queryCondition.getCondition());
		return list;
	}

	@Override
	public int config(RoleRightConfigDto roleRightConfigDto) {
		String roleId = roleRightConfigDto.getRoleId();
		roleRightMapper.deleteByRoleId(roleId);
		List<String> list = roleRightConfigDto.getList();		
		if(list.size() > 0) {
			list.forEach(t->{
				RoleRightEntity roleRightEntity = new RoleRightEntity();
				roleRightEntity.setRightId(UUID.randomUUID().toString().replaceAll("-", ""));
				roleRightEntity.setRoleId(roleId);
				roleRightEntity.setActionId(t);
				roleRightMapper.add(roleRightEntity);
			});
		}
		
		return 0;
	}

	@Override
	public List<String> queryRights(String roleId) {
		return roleRightMapper.queryRights(roleId);
	}

}