package com.zl.mall.shopcenter.orderdetail.entity;


import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * @author coolz
 *
*/
public class OrderDetailEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 订单详情编号
	 *
	 */
	private String orderDetailId;

	/**
	 *
	 * 订单编号
	 *
	 */
	private String orderId;
	
	/**
	 * 产品编号
	 */
	private String prdId;

	/**
	 *
	 * 产品sku_id
	 *
	 */
	private String skuId;

	/**
	 *
	 * 出售价格
	 *
	 */
	private BigDecimal sellPrice;

	/**
	 *
	 * 销售邮费
	 *
	 */
	private BigDecimal sellPostage;

	/**
	 *
	 * 采购价格
	 *
	 */
	private BigDecimal vendorPrice;

	/**
	 *
	 * 采购邮费
	 *
	 */
	private BigDecimal purchasePostage;

	/**
	 *
	 * 销售数量
	 *
	 */
	private Integer sales;

	/**
	 * 店铺id
	 */
	private String shopId;

	public  void setOrderDetailId(String orderDetailId){
		this.orderDetailId = orderDetailId;
	}
	public String getOrderDetailId(){
		return this.orderDetailId;
	}
	public  void setOrderId(String orderId){
		this.orderId = orderId;
	}
	public String getOrderId(){
		return this.orderId;
	}
	public  void setSkuId(String skuId){
		this.skuId = skuId;
	}
	public String getPrdId() {
		return prdId;
	}
	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}
	public String getSkuId(){
		return this.skuId;
	}
	public  void setSellPrice(BigDecimal sellPrice){
		this.sellPrice = sellPrice;
	}
	public BigDecimal getSellPrice(){
		return this.sellPrice;
	}
	public  void setSellPostage(BigDecimal sellPostage){
		this.sellPostage = sellPostage;
	}
	public BigDecimal getSellPostage(){
		return this.sellPostage;
	}
	public  void setVendorPrice(BigDecimal vendorPrice){
		this.vendorPrice = vendorPrice;
	}
	public BigDecimal getVendorPrice(){
		return this.vendorPrice;
	}
	public  void setPurchasePostage(BigDecimal purchasePostage){
		this.purchasePostage = purchasePostage;
	}
	public BigDecimal getPurchasePostage(){
		return this.purchasePostage;
	}
	public  void setSales(Integer sales){
		this.sales = sales;
	}
	public Integer getSales(){
		return this.sales;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
}