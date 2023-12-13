package com.zl.mall.base.historyinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zl.mall.base.historyinfo.dto.HistoryInfoDto;
import com.zl.mall.base.historyinfo.entity.HistoryInfoEntity;
import com.zl.mall.base.historyinfo.service.HistoryInfoService;
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
@RequestMapping("/historyInfo")
public class HistoryInfoController {
	@Autowired
	private HistoryInfoService historyInfoService;
	@PostMapping("/")
	public ResultDto<List<HistoryInfoEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<HistoryInfoEntity> list = historyInfoService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody HistoryInfoEntity historyInfoEntity){
		int num = historyInfoService.add(historyInfoEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody HistoryInfoEntity historyInfoEntity){
		int num = historyInfoService.update(historyInfoEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "id") String id){
		int num = historyInfoService.delete(id);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}	

	@PostMapping("/batchAdd")
	public ResultDto<Integer> batchAdd(@RequestBody HistoryInfoDto historyInfoDto){
		int num = historyInfoService.batchAdd(historyInfoDto);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
}