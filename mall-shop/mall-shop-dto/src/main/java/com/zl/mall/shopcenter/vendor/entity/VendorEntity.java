package com.zl.mall.shopcenter.vendor.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class VendorEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 供应商id
	 *
	 */
	private String vendorId;

	/**
	 *
	 * 供应商名
	 *
	 */
	private String vendorName;

	/**
	 *
	 * 是否实体店
	 *
	 */
	private String conventional;

	/**
	 *
	 * 供应商网址
	 *
	 */
	private String path;

	/**
	 *
	 * 商铺id
	 *
	 */
	private String shopId;

	/**
	 *
	 * 合作状态:0-未合作，1-合作
	 *
	 */
	private String cooperStatus;

	/**
	 *
	 * 创建时间
	 *
	 */
	private String createdDate;

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

	public  void setVendorId(String vendorId){
		this.vendorId = vendorId;
	}
	public String getVendorId(){
		return this.vendorId;
	}
	public  void setVendorName(String vendorName){
		this.vendorName = vendorName;
	}
	public String getVendorName(){
		return this.vendorName;
	}
	public  void setConventional(String conventional){
		this.conventional = conventional;
	}
	public String getConventional(){
		return this.conventional;
	}
	public  void setPath(String path){
		this.path = path;
	}
	public String getPath(){
		return this.path;
	}
	public  void setShopId(String shopId){
		this.shopId = shopId;
	}
	public String getShopId(){
		return this.shopId;
	}
	public  void setCooperStatus(String cooperStatus){
		this.cooperStatus = cooperStatus;
	}
	public String getCooperStatus(){
		return this.cooperStatus;
	}
	public  void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}
	public String getCreatedDate(){
		return this.createdDate;
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
}