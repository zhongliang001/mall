package com.zl.mall.base.role.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zl.mall.base.role.entity.RoleEntity;
import com.zl.mall.base.role.service.RoleService;
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
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@PostMapping("/")
	public ResultDto<List<RoleEntity>> queryList(@RequestBody QueryCondition queryCondition) {
		List<RoleEntity> list = roleService.queryList(queryCondition);
		return ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}

	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody RoleEntity roleEntity) {
		int num = roleService.add(roleEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}

	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody RoleEntity roleEntity) {
		int num = roleService.update(roleEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}

	@GetMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "roleId") String roleId) {
		int num = roleService.delete(roleId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}

	@GetMapping("/queryOwnedRole")
	public ResultDto<List<Map<String, String>>> queryOwnedRole(@RequestParam(value="userId") String userId) {
		List<Map<String, String>> list = roleService.queryOwnedRole(userId);
		return ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@GetMapping("/queryNotOwnedRole")
	public ResultDto<List<Map<String, String>>> queryNotOwnedRole(@RequestParam(value="userId") String userId) {
		List<Map<String, String>> list = roleService.queryNotOwnedRole(userId);
		return ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
}