package com.zl.mall.shopcenter.shopmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.shopcenter.shopmanager.entity.ShopManagerEntity;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface ShopManagerService{
	
	/**
	 * 查询商铺管理员列表
	 * @param queryCondition 查询条件
	 * @return 返回商铺管理原列表
	 */
	public List<ShopManagerEntity> queryList(QueryCondition queryCondition);
	
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
	public int delete(String shopManagerId);
}