package com.zl.mall.user.role.controller;

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
import com.zl.mall.user.role.entity.RoleEntity;
import com.zl.mall.user.role.service.RoleService;
/*
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@PostMapping("/")
	public ResultDto<List<RoleEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<RoleEntity> list = roleService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody RoleEntity roleEntity){
		int num = roleService.add(roleEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody RoleEntity roleEntity){
		int num = roleService.update(roleEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@GetMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "roleId") String roleId){
		int num = roleService.delete(roleId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
}