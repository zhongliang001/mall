package com.zl.mall.base.dict.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zl.mall.base.dict.dto.ModDictDto;
import com.zl.mall.base.dict.entity.DictEntity;
import com.zl.mall.base.dict.service.DictService;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.TradeCodeDict;
import com.zl.mall.common.utils.ResultUtil;
/**
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
	public ResultDto<List<DictEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<DictEntity> list = dictService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody DictEntity dictEntity){
		int num = dictService.add(dictEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody DictEntity dictEntity){
		int num = dictService.update(dictEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "dictId") String dictId){
		int num = dictService.delete(dictId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
	
	@GetMapping("/queryAll")
	public ResultDto< Map<String, List<Map<String, String>>>> queryAll(){
		 Map<String, List<Map<String, String>>> map = dictService.queryAll();
		return ResultUtil.generate(map, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/queryDictTypeList")
	public ResultDto<List<DictEntity>> queryDictTypeList(@RequestBody QueryCondition queryCondition){
		List<DictEntity> list = dictService.queryDictTypeList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/modDict")
	public ResultDto<Integer> modDict(@RequestBody ModDictDto modDictDto){		
		int num = dictService.modDict(modDictDto);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
}