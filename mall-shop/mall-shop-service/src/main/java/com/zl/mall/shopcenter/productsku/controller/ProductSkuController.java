package com.zl.mall.shopcenter.productsku.controller;

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
import com.zl.mall.shopcenter.productsku.entity.ProductSkuEntity;
import com.zl.mall.shopcenter.productsku.service.ProductSkuService;
/**
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/productSku")
public class ProductSkuController {
	@Autowired
	private ProductSkuService productSkuService;
	@PostMapping("/")
	public ResultDto<List<ProductSkuEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<ProductSkuEntity> list = productSkuService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody ProductSkuEntity productSkuEntity){
		int num = productSkuService.add(productSkuEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody ProductSkuEntity productSkuEntity){
		int num = productSkuService.update(productSkuEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@PostMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "skuId") String skuId){
		int num = productSkuService.delete(skuId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
	
	@GetMapping("/queryForSelect")
	public ResultDto<List<SelectDto>> queryForSelect(@RequestParam(value = "prdId") String prdId) {
		List<SelectDto> list = productSkuService.queryForSelect(prdId);
		return ResultUtil.generate(list, TradeCodeDict.SUCCESS_ADD_CODE);
	}
}