package com.zl.mall.shopcenter.productsku.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.SelectDto;
import com.zl.mall.shopcenter.productsku.entity.ProductSkuEntity;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface ProductSkuService{
		/**
	 * 查询商品sku信息列表
	 * @param queryCondition 查询条件
	 * @return 返回商品sku信息列表
		 */
	public List<ProductSkuEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增商品sku信息
	 * @param productSkuEntity 商品sku信息信息 
	 * @return 新增商品sku信息数量
	 */
	public int add(ProductSkuEntity productSkuEntity);
	
	
	/**
	 * 修改商品sku信息
	 * @param productSkuEntity 商品sku信息信息 
	 * @return 修改商品sku信息数量
	 */
	public int update(ProductSkuEntity productSkuEntity);
	
	/**
	 * 删除商品sku信息
	 * @param skuId 商品sku信息
	 * @return 删除商品sku信息数量
	 */
	public int delete(String skuId);
	
	/**
	 * 查询商品sku下拉选
	 * @param prdId 商品id
	 * @return 商品sku选项
	 */
	public List<SelectDto> queryForSelect(String prdId);
}