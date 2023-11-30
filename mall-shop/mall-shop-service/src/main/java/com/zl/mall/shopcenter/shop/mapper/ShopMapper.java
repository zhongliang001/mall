package com.zl.mall.shopcenter.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.shopcenter.shop.entity.ShopEntity;

/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface ShopMapper {

	/**
	 * 查询商铺信息列表
	 * 
	 * @param condition 查询条件
	 * @return 返回商铺列表
	 */
	public List<ShopEntity> queryList(Map<String, Object> condition);

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
	public int delete(@Param("shopId") String shopId);

	/**
	 * 查询用户名下商铺
	 * 
	 * @param userId 用户id
	 * @return 返回商铺列表
	 */
	public List<ShopEntity> getShops(@Param("userId") String userId);
}