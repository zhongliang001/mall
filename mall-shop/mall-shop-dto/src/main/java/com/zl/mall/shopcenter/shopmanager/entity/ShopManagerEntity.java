package com.zl.mall.shopcenter.shopmanager.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class ShopManagerEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 商铺管理者id
	 *
	 */
	private String shopManagerId;

	/**
	 *
	 * 商铺id
	 *
	 */
	private String shopId;

	/**
	 *
	 * 用户id
	 *
	 */
	private String userId;

	/**
	 *
	 * 管理者状态
	 *
	 */
	private String status;

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
	private String careatedUserId;

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

	public  void setShopManagerId(String shopManagerId){
		this.shopManagerId = shopManagerId;
	}
	public String getShopManagerId(){
		return this.shopManagerId;
	}
	public  void setShopId(String shopId){
		this.shopId = shopId;
	}
	public String getShopId(){
		return this.shopId;
	}
	public  void setUserId(String userId){
		this.userId = userId;
	}
	public String getUserId(){
		return this.userId;
	}
	public  void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}
	public  void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}
	public String getCreatedDate(){
		return this.createdDate;
	}
	public  void setCareatedUserId(String careatedUserId){
		this.careatedUserId = careatedUserId;
	}
	public String getCareatedUserId(){
		return this.careatedUserId;
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