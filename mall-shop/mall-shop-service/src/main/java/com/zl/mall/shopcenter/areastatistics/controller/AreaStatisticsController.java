package com.zl.mall.shopcenter.areastatistics.controller;

import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.TradeCodeDict;
import com.zl.mall.common.utils.ResultUtil;
import com.zl.mall.shopcenter.areastatistics.entity.AreaStatisticsEntity;
import com.zl.mall.shopcenter.areastatistics.service.AreaStatisticsService;
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
@RequestMapping("/areaStatistics")
public class AreaStatisticsController {
	private final AreaStatisticsService areaStatisticsService;

	@Inject
	public AreaStatisticsController( AreaStatisticsService areaStatisticsService){
		Assert.notNull(areaStatisticsService, "AreaStatisticsService must not be null!");
		this.areaStatisticsService = areaStatisticsService;
	}

	@PostMapping("/")
	public ResultDto<List<AreaStatisticsEntity>> queryList(@RequestBody QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<AreaStatisticsEntity> list = areaStatisticsService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody AreaStatisticsEntity areaStatisticsEntity){
		int num = areaStatisticsService.add(areaStatisticsEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody AreaStatisticsEntity areaStatisticsEntity){
		int num = areaStatisticsService.update(areaStatisticsEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@GetMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "id") String id){
		int num = areaStatisticsService.delete(id);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
}