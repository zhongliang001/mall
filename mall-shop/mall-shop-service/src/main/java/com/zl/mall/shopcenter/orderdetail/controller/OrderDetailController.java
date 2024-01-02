package com.zl.mall.shopcenter.orderdetail.controller;

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
import com.zl.mall.shopcenter.orderdetail.dto.OrderDetailDto;
import com.zl.mall.shopcenter.orderdetail.entity.OrderDetailEntity;
import com.zl.mall.shopcenter.orderdetail.service.OrderDetailService;
/**
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
	@Autowired
	private OrderDetailService orderDetailService;
	@PostMapping("/")
	public ResultDto<List<OrderDetailDto>> queryList(@RequestBody QueryCondition queryCondition){
		List<OrderDetailDto> list = orderDetailService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody OrderDetailEntity orderDetailEntity){
		int num = orderDetailService.add(orderDetailEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody OrderDetailEntity orderDetailEntity){
		int num = orderDetailService.update(orderDetailEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@GetMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "orderDetailId") String orderDetailId){
		int num = orderDetailService.delete(orderDetailId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
}