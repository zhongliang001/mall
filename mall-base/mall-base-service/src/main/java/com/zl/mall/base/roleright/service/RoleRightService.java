package com.zl.mall.base.roleright.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.zl.mall.base.roleright.dto.RoleRightConfigDto;
import com.zl.mall.base.roleright.dto.RoleRightDto;
import com.zl.mall.base.roleright.entity.RoleRightEntity;
import com.zl.mall.common.dto.QueryCondition;

/*
 * 
 * @author coolz
 *
*/
@Service
public interface RoleRightService {
	public List<RoleRightEntity> queryList(QueryCondition queryCondition);

	public int add(RoleRightEntity roleRightEntity);

	public int update(RoleRightEntity roleRightEntity);

	public int delete(String rightId);

	public List<RoleRightDto> queryDetailList(QueryCondition queryCondition);

	public int config(RoleRightConfigDto roleRightConfigDto);

	public List<String> queryRights(@Param("roleId") String roleId);

}