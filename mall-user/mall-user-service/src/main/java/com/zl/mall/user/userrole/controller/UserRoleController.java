package com.zl.mall.user.userrole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.TradeCodeDict;
import com.zl.mall.common.utils.ResultUtil;
import com.zl.mall.use.userrole.dto.UserRoleDto;
import com.zl.mall.use.userrole.entity.UserRoleEntity;
import com.zl.mall.user.userrole.service.UserRoleService;
/*
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
	@Autowired
	private UserRoleService userRoleService;
	@PostMapping("/")
	public ResultDto<List<UserRoleEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<UserRoleEntity> list = userRoleService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody UserRoleEntity userRoleEntity){
		int num = userRoleService.add(userRoleEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody UserRoleEntity userRoleEntity){
		int num = userRoleService.update(userRoleEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "userRoleId") String userRoleId){
		int num = userRoleService.delete(userRoleId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
	
	@PostMapping("/config")
	public ResultDto<Integer> config(@RequestBody UserRoleDto userRoleDto){
		int num = userRoleService.config(userRoleDto);
		return ResultUtil.generate(0, TradeCodeDict.SUCCESS_ADD_CODE);
	}
}