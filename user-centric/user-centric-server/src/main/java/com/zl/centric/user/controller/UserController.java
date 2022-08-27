package com.zl.centric.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zl.centric.user.entity.UserEntity;
import com.zl.centric.user.service.UserService;
import com.zl.common.dto.QueryCondition;
import com.zl.common.dto.ResultDto;
import com.zl.common.utils.ResultUtil;
/*
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/")
	public ResultDto<List<UserEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<UserEntity> list = userService.queryList(queryCondition);
		return  ResultUtil.genenrate(list, "查询成功");
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody UserEntity userEntity){
		int num = userService.add(userEntity);
		return ResultUtil.genenrate(num, "新增成功");
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody UserEntity userEntity){
		int num = userService.update(userEntity);
		return ResultUtil.genenrate(num, "修改成功");
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "userId") String userId){
		int num = userService.delete(userId);
		return ResultUtil.genenrate(num, "删除成功");
	}
}