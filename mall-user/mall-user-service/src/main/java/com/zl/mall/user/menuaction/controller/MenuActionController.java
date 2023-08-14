package com.zl.mall.user.menuaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.TradeCodeDict;
import com.zl.mall.common.utils.ResultUtil;
import com.zl.mall.user.menuaction.entity.MenuActionEntity;
import com.zl.mall.user.menuaction.service.MenuActionService;
/*
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/menuAction")
public class MenuActionController {
	@Autowired
	private MenuActionService menuActionService;
	@PostMapping("/")
	public ResultDto<List<MenuActionEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<MenuActionEntity> list = menuActionService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody MenuActionEntity menuActionEntity){
		int num = menuActionService.add(menuActionEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody MenuActionEntity menuActionEntity){
		int num = menuActionService.update(menuActionEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@GetMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "actionId") String actionId){
		int num = menuActionService.delete(actionId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
}