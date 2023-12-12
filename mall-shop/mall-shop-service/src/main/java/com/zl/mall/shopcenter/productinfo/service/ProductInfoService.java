package com.zl.mall.shopcenter.productinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.shopcenter.productinfo.dto.ProductInfoDto;
import com.zl.mall.shopcenter.productinfo.entity.ProductInfoEntity;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface ProductInfoService{
		/**
	 * 查询商品信息列表
	 * @param queryCondition 查询条件
	 * @return 返回商品信息列表
		 */
	public List<ProductInfoEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增商品信息
	 * @param productInfoEntity 商品信息信息 
	 * @return 新增商品信息数量
	 */
	public int add(ProductInfoEntity productInfoEntity);
	
	
	/**
	 * 修改商品信息
	 * @param productInfoEntity 商品信息信息 
	 * @return 修改商品信息数量
	 */
	public int update(ProductInfoEntity productInfoEntity);
	
	/**
	 * 删除商品信息
	 * @param prdId 商品信息
	 * @return 删除商品信息数量
	 */
	public int delete(String prdId);

	/**
	 * 新增商品信息
	 * @param productInfoDto 商品信息信息 
	 * @return 新增商品信息数量
	 */
	public int addProduct(ProductInfoDto productInfoDto);

	/**
	 * 修改商品信息
	 * @param productInfoDto 商品信息信息 
	 * @return 修改商品信息数量
	 */
	public int updateProduct(ProductInfoDto productInfoDto);
}