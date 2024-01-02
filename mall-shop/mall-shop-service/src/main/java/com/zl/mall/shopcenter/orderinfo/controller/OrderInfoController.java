package com.zl.mall.shopcenter.orderinfo.controller;

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
import com.zl.mall.shopcenter.orderinfo.dto.OrderDto;
import com.zl.mall.shopcenter.orderinfo.entity.OrderInfoEntity;
import com.zl.mall.shopcenter.orderinfo.service.OrderInfoService;
/**
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {
	@Autowired
	private OrderInfoService orderInfoService;
	@PostMapping("/")
	public ResultDto<List<OrderInfoEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<OrderInfoEntity> list = orderInfoService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@RequestBody OrderInfoEntity orderInfoEntity){
		int num = orderInfoService.add(orderInfoEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@RequestBody OrderInfoEntity orderInfoEntity){
		int num = orderInfoService.update(orderInfoEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@GetMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "orderId") String orderId){
		int num = orderInfoService.delete(orderId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
	
	@PostMapping("/addOrder")
	public ResultDto<Integer> addOrder(@RequestBody OrderDto orderDto){
		int num = orderInfoService.addOrder(orderDto);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	
	@PostMapping("/updateOrder")
	public ResultDto<Integer> updateOrder(@RequestBody OrderDto orderDto){
		int num = orderInfoService.updateOrder(orderDto);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	
	@GetMapping("/deleteOrder")
	public ResultDto<Integer> deleteOrder(@RequestParam(value = "orderId") String orderId){
		int num = orderInfoService.deleteOrder(orderId);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
}