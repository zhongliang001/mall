package com.zl.mall.shopcenter.shopmanager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.shopcenter.shopmanager.entity.ShopManagerEntity;
/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface ShopManagerMapper{
	
	/**
	 * 查询商铺管理员列表
	 * @param condition 查询条件
	 * @return 返回商铺管理原列表
	 */
	public List<ShopManagerEntity> queryList(Map<String, Object> condition);
	
	/**
	 * 新增商铺管理员
	 * @param shopManagerEntity 商铺管理员信息 
	 * @return 新增商铺管理员数量
	 */
	public int add(ShopManagerEntity shopManagerEntity);
	
	/**
	 * 修改商铺管理员
	 * @param shopManagerEntity 商铺管理员信息 
	 * @return 修改商铺管理员数量
	 */
	public int update(ShopManagerEntity shopManagerEntity);
	
	/**
	 * 删除商铺管理员
	 * @param shopManagerId 商铺管理员列表
	 * @return 删除商铺管理员数量
	 */
	public int delete(@Param("shopManagerId") String shopManagerId );
}