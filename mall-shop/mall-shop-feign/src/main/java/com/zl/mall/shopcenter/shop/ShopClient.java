package com.zl.mall.shopcenter.shop;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.shopcenter.shop.entity.ShopEntity;

/**
 * 
 * @author coolz
 *
 */
@FeignClient(name = "${service.shop}", contextId = "shopmanager")
@RequestMapping("/shop")
public interface ShopClient {
	/**
	 * 根据用户id 查询用户名下的商铺
	 * 
	 * @param userId 用户id
	 * @return 用户商铺信息列表
	 */
	@GetMapping("/getShops")
	public ResultDto<List<ShopEntity>> getShops(@RequestParam(value = "userId") String userId);
}
