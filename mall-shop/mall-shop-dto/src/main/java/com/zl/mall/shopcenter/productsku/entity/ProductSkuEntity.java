package com.zl.mall.shopcenter.productsku.entity;


import java.io.Serializable;
import java.math.BigDecimal;

import com.zl.mall.common.annotation.Column;
import com.zl.mall.common.annotation.PrimaryKey;
import com.zl.mall.common.annotation.Record;
import com.zl.mall.common.annotation.Table;
/**
 * 
 * @author coolz
 *
*/
@Table("product_sku")
public class ProductSkuEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 产品sku_id
	 *
	 */
	@PrimaryKey
	@Column(name="sku_id", comment="产品skuid")
	private String skuId;

	/**
	 *
	 * 产品id
	 *
	 */
	private String prdId;

	/**
	 *
	 * 产品sku_name
	 *
	 */
	@Record
	@Column(name="sku_name", comment="sku名")
	private String skuName;

	/**
	 *
	 * 出售价格
	 *
	 */
	@Record
	@Column(name="sell_price", comment="出售价格")
	private BigDecimal sellPrice;

	/**
	 *
	 * 采购价格
	 *
	 */
	@Record
	@Column(name="purch_price", comment="进货价")
	private BigDecimal purchPrice;
	
	/**
	 *
	 * 上架状态
	 *
	 */
	@Record
	@Column(name="state", comment="状态")
	private String state;
	
	/**
	 * 版本号
	 */
	private Integer version;

	public  void setSkuId(String skuId){
		this.skuId = skuId;
	}
	public String getSkuId(){
		return this.skuId;
	}
	public  void setPrdId(String prdId){
		this.prdId = prdId;
	}
	public String getPrdId(){
		return this.prdId;
	}
	public  void setSkuName(String skuName){
		this.skuName = skuName;
	}
	public String getSkuName(){
		return this.skuName;
	}
	public  void setSellPrice(BigDecimal sellPrice){
		this.sellPrice = sellPrice.setScale(2);
	}
	public BigDecimal getSellPrice(){
		return this.sellPrice;
	}
	public  void setPurchPrice(BigDecimal purchPrice){
		this.purchPrice = purchPrice.setScale(2);
	}
	public BigDecimal getPurchPrice(){
		return this.purchPrice;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}