package com.zl.mall.shopcenter.orderdetail.controller;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.TradeCodeDict;
import com.zl.mall.common.utils.ResultUtil;
import com.zl.mall.shopcenter.orderdetail.dto.OrderDetailDto;
import com.zl.mall.shopcenter.orderdetail.entity.OrderDetailEntity;
import com.zl.mall.shopcenter.orderdetail.service.OrderDetailService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
	private final OrderDetailService orderDetailService;

	@Inject
	public OrderDetailController (OrderDetailService orderDetailService){
		Assert.notNull(orderDetailService, "OrderDetailService can not be null");
		this.orderDetailService = orderDetailService;
	}

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
	public ResultDto<Integer> delete(@RequestParam(value = "orderDetailId") String orderDetailId, @RequestParam(value = "shopId") String shopId){
		Map<String, String> map = new HashMap<>(16);
		map.put("orderDetailId", orderDetailId);
		map.put("shopId", shopId);
		int num = orderDetailService.delete(map);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
}