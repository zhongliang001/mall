package com.zl.mall.shopcenter.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.shopcenter.shop.entity.ShopEntity;

/**
 * 
 * @author coolz
 *
*/
@Service
public interface ShopService {

	/**
	 * 查询商铺信息列表
	 * 
	 * @param queryCondition 查询条件
	 * @return 返回商铺列表
	 */
	public List<ShopEntity> queryList(QueryCondition queryCondition);

	/**
	 * 新增商铺
	 * 
	 * @param shopEntity 商铺信息
	 * @return 返回新增数量
	 */
	public int add(ShopEntity shopEntity);

	/**
	 * 修改商铺
	 * 
	 * @param shopEntity 商铺对象
	 * @return 返回修改数量
	 */
	public int update(ShopEntity shopEntity);

	/**
	 * 删除商铺
	 * 
	 * @param shopId 商铺id
	 * @return 删除商铺数量
	 */
	public int delete(String shopId);

	/**
	 * 查询所有商铺
	 * 
	 * @return 返回商铺列表
	 */
	public List<ShopEntity> queryAll();

	/**
	 * 查询用户名下商铺
	 * 
	 * @param userId 用户id
	 * @return 返回商铺列表
	 */
	public List<ShopEntity> getShops(String userId);
}