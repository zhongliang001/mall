package com.zl.mall.user.userauth.controller;

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
import com.zl.mall.user.userauth.entity.UserAuthEntity;
import com.zl.mall.user.userauth.service.UserAuthService;
/*
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/userAuth")
public class UserAuthController {
	@Autowired
	private UserAuthService userAuthService;
	@PostMapping("/")
	public ResultDto<List<UserAuthEntity>> queryList(QueryCondition queryCondition){
		List<UserAuthEntity> list = userAuthService.queryList(queryCondition);
		return  ResultUtil.generate(list, "查询成功");
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody UserAuthEntity userAuthEntity){
		int num = userAuthService.add(userAuthEntity);
		return ResultUtil.generate(num, "新增成功");
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody UserAuthEntity userAuthEntity){
		int num = userAuthService.update(userAuthEntity);
		return ResultUtil.generate(num, "修改成功");
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "userId") String userId){
		int num = userAuthService.delete(userId);
		return ResultUtil.generate(num, "删除成功");
	}
	
	@PostMapping("/login")
	public ResultDto<UserAuthEntity> login(String userName) {
		UserAuthEntity userAuthEntity = userAuthService.login(userName);
		return ResultUtil.generate(userAuthEntity, TradeCodeDict.SUCCESS_QUERRY_CODE);
		
	}
}