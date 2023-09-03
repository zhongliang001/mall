package com.zl.mall.user.userinfo.controller;

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
import com.zl.mall.user.userinfo.entity.UserInfoEntity;
import com.zl.mall.user.userinfo.service.UserInfoService;
/**
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
	@Autowired
	private UserInfoService userInfoService;
	@PostMapping("/")
	public ResultDto<List<UserInfoEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<UserInfoEntity> list = userInfoService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody UserInfoEntity userInfoEntity){
		int num = userInfoService.add(userInfoEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody UserInfoEntity userInfoEntity){
		int num = userInfoService.update(userInfoEntity);
		return ResultUtil.generate(num, "修改成功");
	}
	@GetMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "userId") String userId){
		int num = userInfoService.delete(userId);
		return ResultUtil.generate(num, "删除成功");
	}
	
	@GetMapping("/queryByUserId")
	public ResultDto<UserInfoEntity> queryByUserId(@RequestParam(value = "userId") String userId){
		UserInfoEntity userInfoEntity = userInfoService.queryByUserId(userId);
		return  ResultUtil.generate(userInfoEntity, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
}