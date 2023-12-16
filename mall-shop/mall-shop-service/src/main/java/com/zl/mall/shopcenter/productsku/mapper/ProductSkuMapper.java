package com.zl.mall.shopcenter.productsku.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.common.dto.SelectDto;
import com.zl.mall.shopcenter.productsku.entity.ProductSkuEntity;
/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface ProductSkuMapper{
	/**
	 * 查询商品sku信息列表
	 * @param condition 查询条件
	 * @return 返回商品sku信息列表
		 */
	public List<ProductSkuEntity> queryList(Map<String, Object> condition);
	
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
	public int delete(@Param("skuId") String skuId );

	/**
	 * 查询商品sku下拉选
	 * @param prdId 商品id
	 * @return 商品sku选项
	 */
	public List<SelectDto> queryForSelect(String prdId);
}