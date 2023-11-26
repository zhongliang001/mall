package com.zl.mall.shopcenter.shop.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class ShopEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 商铺id
	 *
	 */
	private String shopId;

	/**
	 *
	 * 商铺名
	 *
	 */
	private String shopName;

	/**
	 *
	 * 是否实体店
	 *
	 */
	private String conventional;

	/**
	 *
	 * 铺货渠道
	 *
	 */
	private String channel;

	/**
	 *
	 * 网店地址
	 *
	 */
	private String path;

	/**
	 *
	 * 店铺管理地址
	 *
	 */
	private String managePath;

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

	public  void setShopId(String shopId){
		this.shopId = shopId;
	}
	public String getShopId(){
		return this.shopId;
	}
	public  void setShopName(String shopName){
		this.shopName = shopName;
	}
	public String getShopName(){
		return this.shopName;
	}
	public  void setConventional(String conventional){
		this.conventional = conventional;
	}
	public String getConventional(){
		return this.conventional;
	}
	public  void setChannel(String channel){
		this.channel = channel;
	}
	public String getChannel(){
		return this.channel;
	}
	public  void setPath(String path){
		this.path = path;
	}
	public String getPath(){
		return this.path;
	}
	public  void setManagePath(String managePath){
		this.managePath = managePath;
	}
	public String getManagePath(){
		return this.managePath;
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