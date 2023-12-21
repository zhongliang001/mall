package com.zl.mall.shopcenter.vendor.controller;

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
import com.zl.mall.shopcenter.vendor.entity.VendorEntity;
import com.zl.mall.shopcenter.vendor.service.VendorService;
/**
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/vendor")
public class VendorController {
	@Autowired
	private VendorService vendorService;
	@PostMapping("/")
	public ResultDto<List<VendorEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<VendorEntity> list = vendorService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody VendorEntity vendorEntity){
		int num = vendorService.add(vendorEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody VendorEntity vendorEntity){
		int num = vendorService.update(vendorEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@GetMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "vendorId") String vendorId){
		int num = vendorService.delete(vendorId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
	
	@GetMapping("/queryForSelect")
	public ResultDto<List<SelectDto>> queryForSelect(@RequestParam(value = "shopId") String shopId) {
		List<SelectDto> list = vendorService.queryForSelect(shopId);
		return ResultUtil.generate(list, TradeCodeDict.SUCCESS_ADD_CODE);
	}
}