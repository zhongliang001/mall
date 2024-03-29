package com.zl.mall.shopcenter.orderinfo.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class OrderInfoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 订单编号
	 *
	 */
	private String orderId;
	
	/**
	 *
	 * 购买者id
	 *
	 */
	private String buyerId;

	/**
	 *
	 * 购买时间
	 *
	 */
	private String purchaseTime;

	/**
	 *
	 * 平台订单号
	 *
	 */
	private String platOrderId;

	/**
	 *
	 * 采购订单号
	 *
	 */
	private String vendorOrderId;

	/**
	 *
	 * 商铺id
	 */
	private String shopId;

	/**
	 * 
	 * 用户地址
	 *
	 */
	private String area;

	/**
	 *
	 * 购买状态
	 *
	 */
	private String state;

	/**
	 *
	 * 创建日期
	 *
	 */
	private String createDate;

	/**
	 *
	 * 创建人id
	 *
	 */
	private String createdUserId;

	/**
	 *
	 * 修改时间
	 *
	 */
	private String updateDate;

	/**
	 *
	 * 修改人id
	 *
	 */
	private String updateUserId;

	public  void setOrderId(String orderId){
		this.orderId = orderId;
	}
	public String getOrderId(){
		return this.orderId;
	}
	
	public  void setBuyerId(String buyerId){
		this.buyerId = buyerId;
	}
	public String getBuyerId(){
		return this.buyerId;
	}
	public  void setPurchaseTime(String purchaseTime){
		this.purchaseTime = purchaseTime;
	}
	public String getPurchaseTime(){
		return this.purchaseTime;
	}
	public  void setPlatOrderId(String platOrderId){
		this.platOrderId = platOrderId;
	}
	public String getPlatOrderId(){
		return this.platOrderId;
	}
	public  void setVendorOrderId(String vendorOrderId){
		this.vendorOrderId = vendorOrderId;
	}
	public String getVendorOrderId(){
		return this.vendorOrderId;
	}
	public  void setArea(String area){
		this.area = area;
	}
	public String getArea(){
		return this.area;
	}
	public  void setState(String state){
		this.state = state;
	}
	public String getState(){
		return this.state;
	}
	public  void setCreateDate(String createDate){
		this.createDate = createDate;
	}
	public String getCreateDate(){
		return this.createDate;
	}
	public  void setCreatedUserId(String createdUserId){
		this.createdUserId = createdUserId;
	}
	public String getCreatedUserId(){
		return this.createdUserId;
	}
	public  void setUpdateDate(String updateDate){
		this.updateDate = updateDate;
	}
	public String getUpdateDate(){
		return this.updateDate;
	}
	public  void setUpdateUserId(String updateUserId){
		this.updateUserId = updateUserId;
	}
	public String getUpdateUserId(){
		return this.updateUserId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
}