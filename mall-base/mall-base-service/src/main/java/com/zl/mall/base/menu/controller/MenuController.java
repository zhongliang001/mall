package com.zl.mall.base.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zl.mall.base.menu.entity.MenuEntity;
import com.zl.mall.base.menu.service.MenuService;
import com.zl.mall.base.menuaction.dto.MenuActionDto;
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
	
	@GetMapping("/selectMenuByUserId")
	public ResultDto<List<MenuActionDto>> selectMenuByUserId(@RequestParam(value = "userId") String userId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		List<MenuActionDto> list = menuService.selectMenuByUserId(map);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
		
	}
}