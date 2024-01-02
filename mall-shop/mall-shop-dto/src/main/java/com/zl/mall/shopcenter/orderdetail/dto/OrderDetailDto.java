package com.zl.mall.shopcenter.orderdetail.dto;


import java.io.Serializable;

import com.zl.mall.shopcenter.orderdetail.entity.OrderDetailEntity;
/**
 * 
 * @author coolz
 *
*/
public class OrderDetailDto extends OrderDetailEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * 产品id
	 *
	 */
	private String prdName;

	/**
	 *
	 * 产品sku_id
	 *
	 */
	private String skuName;

	public String getPrdName() {
		return prdName;
	}

	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	
}