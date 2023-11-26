package com.zl.mall.shopcenter.shopmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.zl.mall.common.dto.QueryCondition;

import com.zl.mall.shopcenter.shopmanager.entity.ShopManagerEntity;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface ShopManagerService{
	public List<ShopManagerEntity> queryList(QueryCondition queryCondition);
	
	public int add(ShopManagerEntity shopManagerEntity);
	
	public int update(ShopManagerEntity shopManagerEntity);
	
	public int delete(String shopManagerId);
}