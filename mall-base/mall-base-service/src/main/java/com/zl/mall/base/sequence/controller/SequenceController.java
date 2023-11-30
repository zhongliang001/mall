package com.zl.mall.base.sequence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zl.mall.base.sequence.entity.SequenceEntity;
import com.zl.mall.base.sequence.service.SequenceService;
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
@RequestMapping("/sequence")
public class SequenceController {
	@Autowired
	private SequenceService sequenceService;
	@PostMapping("/")
	public ResultDto<List<SequenceEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<SequenceEntity> list = sequenceService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody SequenceEntity sequenceEntity){
		int num = sequenceService.add(sequenceEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody SequenceEntity sequenceEntity){
		int num = sequenceService.update(sequenceEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "seqId") String seqId){
		int num = sequenceService.delete(seqId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
}