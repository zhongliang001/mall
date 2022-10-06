package com.zl.centric.action.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.centric.action.dto.ActionDto;
import com.zl.centric.action.entity.ActionEntity;
import com.zl.centric.action.mapper.ActionMapper;
import com.zl.centric.action.service.ActionService;
import com.zl.common.dto.QueryCondition;
import com.zl.common.exception.ZlException;
/**
 * 
 * @author coolz
 *
*/
@Service
public class ActionServiceImpl implements ActionService {
	
	private Logger logger = LoggerFactory.getLogger(ActionServiceImpl.class);
	
	@Autowired
	private ActionMapper actionMapper;
	public List<ActionEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<ActionEntity> list = actionMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(ActionEntity actionEntity) throws ZlException{
		logger.info("接收到新增菜单操作：{}", actionEntity);
		isExsist(actionEntity);
		return actionMapper.add(actionEntity);
	}

	private void isExsist(ActionEntity actionEntity) throws ZlException {
		Map<String, Object> queryMap = new HashMap<String, Object>(16);
		queryMap.put("oper", actionEntity.getOper());
		queryMap.put("menuId", actionEntity.getMenuId());
		List<ActionEntity> queryList = actionMapper.queryList(queryMap);
		if(queryList.size() > 0) {
			for(int i = 0; i < queryList.size(); i ++) {
				ActionEntity actionEntity2 = queryList.get(i);
				if(!actionEntity2.getActionId().equals(actionEntity.getActionId())) {
					logger.info("菜单操作：{}已经存在", actionEntity.getOper());
					throw new ZlException("菜单操作："+ actionEntity.getOper() + "已经存在");				
				}
			}
		}
	}
	
	public int update(ActionEntity actionEntity) throws ZlException {
		logger.info("接收到修改菜单操作：{}", actionEntity);
		isExsist(actionEntity);
		return actionMapper.update(actionEntity);
	}
	public int delete(String actionId){
		return actionMapper.delete(actionId);
	}
	public List<ActionDto> queryMenuAction(QueryCondition queryCondition){
		logger.info("接收到查询菜单列表的条件: {}", queryCondition.getCondition());
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<ActionDto> list = actionMapper.queryMenuAction(queryCondition.getCondition());
		return list;
	}

	@Override
	public ActionDto detail(String actionId) {
		logger.info("接收到菜单操作详情：{}", actionId);
		Map<String, Object> queryMap = new HashMap<String, Object>(16);
		queryMap.put("actionId", actionId);
		List<ActionDto> queryList = actionMapper.queryMenuAction(queryMap);
		return queryList.get(0);
	}
}