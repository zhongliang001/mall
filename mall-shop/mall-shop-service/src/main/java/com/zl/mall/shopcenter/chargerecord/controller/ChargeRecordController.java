package com.zl.mall.shopcenter.chargerecord.controller;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.TradeCodeDict;
import com.zl.mall.common.utils.ResultUtil;
import com.zl.mall.shopcenter.chargerecord.entity.ChargeRecordEntity;
import com.zl.mall.shopcenter.chargerecord.service.ChargeRecordService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
/**
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/chargeRecord")
public class ChargeRecordController {
	private final ChargeRecordService chargeRecordService;
	@Inject
	public ChargeRecordController(ChargeRecordService chargeRecordService){
		Assert.notNull(chargeRecordService, "ChargeRecordService must not be null!");
		this.chargeRecordService = chargeRecordService;
	}
	@PostMapping("/")
	public ResultDto<List<ChargeRecordEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<ChargeRecordEntity> list = chargeRecordService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody ChargeRecordEntity chargeRecordEntity){
		int num = chargeRecordService.add(chargeRecordEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody ChargeRecordEntity chargeRecordEntity){
		int num = chargeRecordService.update(chargeRecordEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@GetMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "id") String id){
		int num = chargeRecordService.delete(id);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
}