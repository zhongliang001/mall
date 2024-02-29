package com.zl.mall.shopcenter.ztcstatistics.controller;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.TradeCodeDict;
import com.zl.mall.common.utils.ResultUtil;
import com.zl.mall.shopcenter.ztcstatistics.entity.ZtcStatisticsEntity;
import com.zl.mall.shopcenter.ztcstatistics.service.ZtcStatisticsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/**
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/ztcStatistics")
public class ZtcStatisticsController {
	private final ZtcStatisticsService ztcStatisticsService;

	public  ZtcStatisticsController(ZtcStatisticsService ztcStatisticsService){
		this.ztcStatisticsService = ztcStatisticsService;
	}
	@PostMapping("/")
	public ResultDto<List<ZtcStatisticsEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<ZtcStatisticsEntity> list = ztcStatisticsService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody ZtcStatisticsEntity ztcStatisticsEntity){
		int num = ztcStatisticsService.add(ztcStatisticsEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody ZtcStatisticsEntity ztcStatisticsEntity){
		int num = ztcStatisticsService.update(ztcStatisticsEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "id") String id){
		int num = ztcStatisticsService.delete(id);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}

	@RequestMapping(value = "/file/upload")
	public ResultDto<Integer>  fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("shopId") String shopId) {
		int num = ztcStatisticsService.fileUpload(file, shopId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
}