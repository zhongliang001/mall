package com.zl.centric.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.zl.centric.menu.dto.MenuDto;
import com.zl.centric.menu.entity.MenuEntity;
import com.zl.centric.menu.service.MenuService;
import com.zl.common.dto.QueryCondition;
import com.zl.common.dto.ResultDto;
import com.zl.common.utils.ResultUtil;
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
	public ResultDto<List<MenuDto>> queryList(@RequestBody QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<MenuEntity> list = menuService.queryList(queryCondition);		
		return  ResultUtil.genenrateList(list, MenuDto.class);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody MenuEntity menuEntity){
		int num = menuService.add(menuEntity);
		return ResultUtil.genenrate(num, "新增成功");
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody MenuEntity menuEntity){
		int num = menuService.update(menuEntity);
		return ResultUtil.genenrate(num, "修改成功");
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "menuId") String menuId){
		int num = menuService.delete(menuId);
		return ResultUtil.genenrate(num, "删除成功");
	}
}