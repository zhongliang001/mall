package com.zl.mall.base.areacode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zl.mall.base.areacode.dto.AreaCodeDto;
import com.zl.mall.base.areacode.entity.AreaCodeEntity;
import com.zl.mall.base.areacode.service.AreaCodeService;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.SelectDto;
import com.zl.mall.common.dto.TradeCodeDict;
import com.zl.mall.common.utils.ResultUtil;
/**
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/areaCode")
public class AreaCodeController {
	@Autowired
	private AreaCodeService areaCodeService;
	@PostMapping("/")
	public ResultDto<List<AreaCodeEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<AreaCodeEntity> list = areaCodeService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody AreaCodeEntity areaCodeEntity){
		int num = areaCodeService.add(areaCodeEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody AreaCodeEntity areaCodeEntity){
		int num = areaCodeService.update(areaCodeEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(){
		int num = areaCodeService.delete();
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
	
	@GetMapping("/queryProvince")
	public  ResultDto<List<SelectDto>> queryProvince(){
		List<SelectDto> list = areaCodeService.queryProvince();
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@GetMapping("/queryCtiy")
	public  ResultDto<List<SelectDto>> queryCtiy(@RequestParam("province") String province){
		List<SelectDto> list = areaCodeService.queryCtiy(province);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@GetMapping("/queryArea")
	public  ResultDto<List<SelectDto>> queryArea(@RequestParam("city") String city){
		List<SelectDto> list = areaCodeService.queryArea(city);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
}