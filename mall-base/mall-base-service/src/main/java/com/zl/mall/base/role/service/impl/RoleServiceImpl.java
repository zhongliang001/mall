package com.zl.mall.base.role.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.role.entity.RoleEntity;
import com.zl.mall.base.role.mapper.RoleMapper;
import com.zl.mall.base.role.service.RoleService;
import com.zl.mall.base.template.service.TemplateService;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;

/**
 * 
 * @author coolz
 *
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private TemplateService templateService;

	@Override
	public List<RoleEntity> queryList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<RoleEntity> list = roleMapper.queryList(queryCondition.getCondition());
		return list;
	}

	@Override
	public int add(RoleEntity roleEntity) {
		String seqno = templateService.getSeqno(TempConstant.ROLE_TEMP);
		if(StringUtils.isNotEmpty(seqno)) {
			roleEntity.setRoleId(seqno);
		}else {
			roleEntity.setRoleId(UUID.randomUUID().toString().replaceAll("-", ""));			
		}
		return roleMapper.add(roleEntity);
	}

	@Override
	public int update(RoleEntity roleEntity) {
		return roleMapper.update(roleEntity);
	}

	@Override
	public int delete(String roleId) {
		return roleMapper.delete(roleId);
	}

	@Override
	public List<Map<String, String>> queryOwnedRole(String userId){
		return roleMapper.queryOwnedRole(userId);
	}

	@Override
	public List<Map<String, String>> queryNotOwnedRole(String userId) {
		return roleMapper.queryNotOwnedRole(userId);
	}
}