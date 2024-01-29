package com.zl.mall.shopcenter.productinfo.service;

import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.SelectDto;
import com.zl.mall.shopcenter.productinfo.dto.ProductInfoDto;
import com.zl.mall.shopcenter.productinfo.entity.ProductInfoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
	List<ProductInfoEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增商品信息
	 * @param productInfoEntity 商品信息信息 
	 * @return 新增商品信息数量
	 */
	int add(ProductInfoEntity productInfoEntity);
	
	
	/**
	 * 修改商品信息
	 * @param productInfoEntity 商品信息信息 
	 * @return 修改商品信息数量
	 */
	int update(ProductInfoEntity productInfoEntity);
	
	/**
	 * 删除商品信息
	 * @param prdId 商品信息
	 * @return 删除商品信息数量
	 */
	int delete(String prdId);

	/**
	 * 新增商品信息
	 * @param productInfoDto 商品信息信息 
	 * @return 新增商品信息数量
	 */
	int addProduct(ProductInfoDto productInfoDto);

	/**
	 * 修改商品信息
	 * @param productInfoDto 商品信息信息 
	 * @return 修改商品信息数量
	 */
	int updateProduct(ProductInfoDto productInfoDto);
	
	/**
	 * 查询商品下拉选
	 * @param shopId 店铺id
	 * @return 商品选项
	 */
	List<SelectDto> queryForSelect(String shopId);
}