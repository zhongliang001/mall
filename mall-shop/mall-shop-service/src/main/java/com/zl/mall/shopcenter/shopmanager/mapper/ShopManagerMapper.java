package com.zl.mall.shopcenter.shopmanager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.shopcenter.shopmanager.entity.ShopManagerEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface ShopManagerMapper{
	public List<ShopManagerEntity> queryList(Map<String, Object> condition);
	
	public int add(ShopManagerEntity shopManagerEntity);
	
	public int update(ShopManagerEntity shopManagerEntity);
	
	public int delete(@Param("shopManagerId") String shopManagerId );
}