package com.zl.mall.user.dict.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.user.dict.dto.DictDto;
import com.zl.mall.user.dict.entity.DictEntity;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface DictService{
	
	/**
	 * 查询用户授权信息列表
	 * @param condtion 查询条件
	 * @return
	 */
	public List<DictEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增用户授权信息
	 * @param userAuthEntity 用户授权信息
	 * @return
	 */
	public int add(DictEntity dictEntity);
	
	/**
	 * 修改用户授权信息
	 * @param userAuthEntity 用户授权信息
	 * @return
	 */
	public int update(DictEntity dictEntity);
	
	/**
	 * 删除用户授权信息
	 * @param userId 用户id
	 * @return
	 */
	public int delete(String dictId);
	
	/**
	 * 查询所有的字典项
	 * @return
	 */
	public Map<String, List<Map<String, String>>> queryAll();
}