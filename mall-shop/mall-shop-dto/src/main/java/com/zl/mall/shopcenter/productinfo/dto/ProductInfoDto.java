package com.zl.mall.shopcenter.productinfo.dto;

import java.io.Serializable;
import java.util.List;

import com.zl.mall.shopcenter.productinfo.entity.ProductInfoEntity;
import com.zl.mall.shopcenter.productsku.entity.ProductSkuEntity;
/**
 * 
 * @author coolz
 *
 */
public class ProductInfoDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProductInfoEntity productInfo;
	
	private List<ProductSkuEntity> list;
	
	private List<ProductSkuEntity> delList;

	public ProductInfoEntity getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfoEntity productInfo) {
		this.productInfo = productInfo;
	}

	public List<ProductSkuEntity> getList() {
		return list;
	}

	public void setList(List<ProductSkuEntity> list) {
		this.list = list;
	}

	public List<ProductSkuEntity> getDelList() {
		return delList;
	}

	public void setDelList(List<ProductSkuEntity> delList) {
		this.delList = delList;
	}
	
	
}
