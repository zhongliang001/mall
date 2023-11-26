package com.zl.mall.shopcenter.shop.controller;

import java.util.List;

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
import com.zl.mall.shopcenter.shop.entity.ShopEntity;
import com.zl.mall.shopcenter.shop.service.ShopService;
/*
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/shop")
public class ShopController {
	@Autowired
	private ShopService shopService;
	@PostMapping("/")
	public ResultDto<List<ShopEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<ShopEntity> list = shopService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody ShopEntity shopEntity){
		int num = shopService.add(shopEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody ShopEntity shopEntity){
		int num = shopService.update(shopEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "shopId") String shopId){
		int num = shopService.delete(shopId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
	
	@GetMapping("/queryAll")
	public ResultDto<List<ShopEntity>> queryAll(){
		List<ShopEntity> list = shopService.queryAll();
		return ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
}