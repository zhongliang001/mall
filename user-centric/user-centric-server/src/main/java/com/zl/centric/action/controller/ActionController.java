package com.zl.centric.action.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zl.centric.action.dto.ActionDto;
import com.zl.centric.action.entity.ActionEntity;
import com.zl.centric.action.service.ActionService;
import com.zl.common.dto.QueryCondition;
import com.zl.common.dto.ResultDto;
import com.zl.common.exception.ZlException;
import com.zl.common.utils.ResultUtil;
/**
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/action")
public class ActionController {
	@Autowired
	private ActionService actionService;
	
	@PostMapping("/")
	public ResultDto<List<ActionEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<ActionEntity> list = actionService.queryList(queryCondition);
		return  ResultUtil.genenrate(list, "查询成功");
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody ActionEntity actionEntity) throws ZlException{
		int num = actionService.add(actionEntity);
		return ResultUtil.genenrate(num, "新增成功");
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody ActionEntity actionEntity) throws ZlException{
		int num = actionService.update(actionEntity);
		return ResultUtil.genenrate(num, "修改成功");
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "actionId") String actionId){
		int num = actionService.delete(actionId);
		return ResultUtil.genenrate(num, "删除成功");
	}
	
	@PostMapping("/queryMenuAction")
	public ResultDto<List<ActionDto>> queryMenuAction(@RequestBody QueryCondition queryCondition){
		List<ActionDto> list = actionService.queryMenuAction(queryCondition);
		return  ResultUtil.genenrate(list, "查询成功");
	}
	
	@PostMapping("/detail")
	public ResultDto<ActionDto> detail(@RequestParam(value = "actionId") String actionId){
		ActionDto detail = actionService.detail(actionId);
		return  ResultUtil.genenrate(detail, "查询成功");
	}
}