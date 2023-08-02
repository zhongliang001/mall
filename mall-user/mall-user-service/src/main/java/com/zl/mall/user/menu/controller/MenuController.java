package com.zl.mall.user.menu.controller;

import java.util.List;
import java.util.Map;

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
import com.zl.mall.user.menu.entity.MenuEntity;
import com.zl.mall.user.menu.service.MenuService;
/*
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;
	@PostMapping("/")
	public ResultDto<List<MenuEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<MenuEntity> list = menuService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody MenuEntity menuEntity){
		int num = menuService.add(menuEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody MenuEntity menuEntity){
		int num = menuService.update(menuEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@GetMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "menuId") String menuId){
		int num = menuService.delete(menuId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
	
	@GetMapping("/selectRoot")
	public ResultDto<List<Map<String, String>>> selectRoot(){
		List<Map<String, String>> selectRoot = menuService.selectRoot();
		return ResultUtil.generate(selectRoot, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
}