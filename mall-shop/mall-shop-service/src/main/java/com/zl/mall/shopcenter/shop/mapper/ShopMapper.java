package com.zl.mall.shopcenter.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.shopcenter.shop.entity.ShopEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface ShopMapper{
	public List<ShopEntity> queryList(Map<String, Object> condition);
	
	public int add(ShopEntity shopEntity);
	
	public int update(ShopEntity shopEntity);
	
	public int delete(@Param("shopId") String shopId );
}