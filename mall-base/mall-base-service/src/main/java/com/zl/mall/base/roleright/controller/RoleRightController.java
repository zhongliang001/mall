package com.zl.mall.base.roleright.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zl.mall.base.roleright.dto.RoleRightConfigDto;
import com.zl.mall.base.roleright.dto.RoleRightDto;
import com.zl.mall.base.roleright.entity.RoleRightEntity;
import com.zl.mall.base.roleright.service.RoleRightService;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.TradeCodeDict;
import com.zl.mall.common.utils.ResultUtil;

/*
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/roleRight")
public class RoleRightController {
	@Autowired
	private RoleRightService roleRightService;

	@PostMapping("/")
	public ResultDto<List<RoleRightEntity>> queryList(@RequestBody QueryCondition queryCondition) {
		List<RoleRightEntity> list = roleRightService.queryList(queryCondition);
		return ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}

	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody RoleRightEntity roleRightEntity) {
		int num = roleRightService.add(roleRightEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}

	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody RoleRightEntity roleRightEntity) {
		int num = roleRightService.update(roleRightEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}

	@GetMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "rightId") String rightId) {
		int num = roleRightService.delete(rightId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}

	@PostMapping("/queryDetailList")
	public ResultDto<List<RoleRightDto>> queryDetailList(@RequestBody QueryCondition queryCondition) {
		List<RoleRightDto> list = roleRightService.queryDetailList(queryCondition);
		return ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}

	@PostMapping("/config")
	public ResultDto<Integer> config(@RequestBody RoleRightConfigDto roleRightConfigDto) {
		int num = roleRightService.config(roleRightConfigDto);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}

	@GetMapping("/queryRights")
	public ResultDto<List<String>> queryRights(@RequestParam(value = "roleId") String roleId) {
		List<String> queryRights = roleRightService.queryRights(roleId);
		return ResultUtil.generate(queryRights, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}	

}