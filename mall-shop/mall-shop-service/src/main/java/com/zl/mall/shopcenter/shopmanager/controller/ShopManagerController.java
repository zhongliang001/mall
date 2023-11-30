package com.zl.mall.shopcenter.shopmanager.controller;

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
import com.zl.mall.shopcenter.shopmanager.entity.ShopManagerEntity;
import com.zl.mall.shopcenter.shopmanager.service.ShopManagerService;
/**
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/shopManager")
public class ShopManagerController {
	@Autowired
	private ShopManagerService shopManagerService;
	@PostMapping("/")
	public ResultDto<List<ShopManagerEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<ShopManagerEntity> list = shopManagerService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody ShopManagerEntity shopManagerEntity){		
		int num = shopManagerService.add(shopManagerEntity);	
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody ShopManagerEntity shopManagerEntity){
		int num = shopManagerService.update(shopManagerEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "shopManagerId") String shopManagerId){
		int num = shopManagerService.delete(shopManagerId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
}