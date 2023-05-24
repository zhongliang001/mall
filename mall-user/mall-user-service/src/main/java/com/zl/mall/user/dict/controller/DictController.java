package com.zl.mall.user.dict.controller;

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
import com.zl.mall.user.dict.entity.DictEntity;
import com.zl.mall.user.dict.service.DictService;
/*
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/dict")
public class DictController {
	@Autowired
	private DictService dictService;
	@PostMapping("/")
	public ResultDto<List<DictEntity>> queryList(QueryCondition queryCondition){
		List<DictEntity> list = dictService.queryList(queryCondition);
		return  ResultUtil.generate(list, "查询成功");
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody DictEntity dictEntity){
		int num = dictService.add(dictEntity);
		return ResultUtil.generate(num, "新增成功");
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody DictEntity dictEntity){
		int num = dictService.update(dictEntity);
		return ResultUtil.generate(num, "修改成功");
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "dictId") String dictId){
		int num = dictService.delete(dictId);
		return ResultUtil.generate(num, "删除成功");
	}
	
	@GetMapping("/queryAll")
	public ResultDto< Map<String, List<Map<String, String>>>> queryAll(){
		 Map<String, List<Map<String, String>>> map = dictService.queryAll();
		return ResultUtil.generate(map, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
}