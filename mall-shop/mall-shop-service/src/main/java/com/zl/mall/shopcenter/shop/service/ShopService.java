package com.zl.mall.shopcenter.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.shopcenter.shop.entity.ShopEntity;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface ShopService{
	public List<ShopEntity> queryList(QueryCondition queryCondition);
	
	public int add(ShopEntity shopEntity);
	
	public int update(ShopEntity shopEntity);
	
	public int delete(String shopId);
	
	public List<ShopEntity> queryAll();
	
	public List<ShopEntity> getShops(String userId);
}