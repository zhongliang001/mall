package com.zl.mall.shopcenter.productinfo.controller;

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
import com.zl.mall.common.dto.SelectDto;
import com.zl.mall.common.dto.TradeCodeDict;
import com.zl.mall.common.utils.ResultUtil;
import com.zl.mall.shopcenter.productinfo.dto.ProductInfoDto;
import com.zl.mall.shopcenter.productinfo.entity.ProductInfoEntity;
import com.zl.mall.shopcenter.productinfo.service.ProductInfoService;
/**
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/productInfo")
public class ProductInfoController {
	@Autowired
	private ProductInfoService productInfoService;
	@PostMapping("/")
	public ResultDto<List<ProductInfoEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<ProductInfoEntity> list = productInfoService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody ProductInfoEntity productInfoEntity){
		int num = productInfoService.add(productInfoEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody ProductInfoEntity productInfoEntity){
		int num = productInfoService.update(productInfoEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@GetMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "prdId") String prdId){
		int num = productInfoService.delete(prdId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
	
	@PostMapping("/addProduct")
	public ResultDto<Integer> addProduct(@RequestBody ProductInfoDto productInfoDto){
		int num = productInfoService.addProduct(productInfoDto);		
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	
	@PostMapping("/updateProduct")
	public ResultDto<Integer> updateProduct(@RequestBody ProductInfoDto productInfoDto){
		int num = productInfoService.updateProduct(productInfoDto);		
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	
	@GetMapping("/queryForSelect")
	public ResultDto<List<SelectDto>> queryForSelect(@RequestParam(value = "shopId") String shopId) {
		List<SelectDto> list = productInfoService.queryForSelect(shopId);
		return ResultUtil.generate(list, TradeCodeDict.SUCCESS_ADD_CODE);
	}
}