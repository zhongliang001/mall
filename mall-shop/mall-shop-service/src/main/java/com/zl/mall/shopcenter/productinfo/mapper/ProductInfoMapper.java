package com.zl.mall.shopcenter.productinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.common.dto.SelectDto;
import com.zl.mall.shopcenter.productinfo.entity.ProductInfoEntity;
/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface ProductInfoMapper{
	/**
	 * 查询商品信息列表
	 * @param condition 查询条件
	 * @return 返回商品信息列表
		 */
	public List<ProductInfoEntity> queryList(Map<String, Object> condition);
	
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
	public int delete(@Param("prdId") String prdId );
	
	
	/**
	 * 查询商品下拉选
	 * @param shopId 店铺id
	 * @return 商品选项
	 */
	public List<SelectDto> queryForSelect(@Param("shopId") String shopId);
}