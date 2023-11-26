package com.zl.mall.base.userrole.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.template.service.TemplateService;
import com.zl.mall.base.userrole.dto.UserRoleDto;
import com.zl.mall.base.userrole.entity.UserRoleEntity;
import com.zl.mall.base.userrole.mapper.UserRoleMapper;
import com.zl.mall.base.userrole.service.UserRoleService;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
/**
 * 
 * @author coolz
 *
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private TemplateService templateService;
	
	@Override
	public List<UserRoleEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<UserRoleEntity> list = userRoleMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	@Override	
	public int add(UserRoleEntity userRoleEntity){
		String seqno = templateService.getSeqno(TempConstant.USER_ROLE_TEMP);
		userRoleEntity.setUserRoleId(seqno);
		
		return userRoleMapper.add(userRoleEntity);
	}
	
	@Override
	public int update(UserRoleEntity userRoleEntity){
		return userRoleMapper.update(userRoleEntity);
	}
	
	@Override
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