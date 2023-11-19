package com.zl.mall.base.sequnce.controller;

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
import com.zl.mall.base.sequnce.entity.SequnceEntity;
import com.zl.mall.base.sequnce.service.SequnceService;
/*
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/sequnce")
public class SequnceController {
	@Autowired
	private SequnceService sequnceService;
	@PostMapping("/")
	public ResultDto<List<SequnceEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<SequnceEntity> list = sequnceService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody SequnceEntity sequnceEntity){
		int num = sequnceService.add(sequnceEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody SequnceEntity sequnceEntity){
		int num = sequnceService.update(sequnceEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "seqId") String seqId){
		int num = sequnceService.delete(seqId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
}