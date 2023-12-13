package com.zl.mall.shopcenter.productinfo.entity;


import java.io.Serializable;

import com.zl.mall.common.annotation.Column;
import com.zl.mall.common.annotation.PrimaryKey;
import com.zl.mall.common.annotation.Record;
import com.zl.mall.common.annotation.Table;
/**
 * 
 * @author coolz
 *
*/
@Table("product_info")
public class ProductInfoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 产品id
	 *
	 */
	@PrimaryKey
	@Column(name="prd_id", comment="产品id")
	private String prdId;

	/**
	 *
	 * 商品名
	 *
	 */
	@Record
	@Column(name="prd_name", comment="产品名")
	private String prdName;

	/**
	 *
	 * 商品类型
	 *
	 */
	@Record
	@Column(name="prd_type", comment="产品类型")
	private String prdType;

	/**
	 *
	 * 平台商品id
	 *
	 */
	@Record
	@Column(name="platform_prd_id", comment="平台商品id")
	private String platformPrdId;

	/**
	 *
	 * 供应商id
	 *
	 */
	@Record
	@Column(name="vendor_id", comment="供应商id")
	private String vendorId;

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

	/**
	 *
	 * 上架状态
	 *
	 */
	@Record
	@Column(name="state", comment="商品状态")
	private String state;

	/**
	 *
	 * 上架日期
	 *
	 */
	private String grdDate;

	/**
	 *
	 * shop_id
	 *
	 */
	private String shopId;

	/**
	 * 版本号
	 */
	private Integer version;

	public  void setPrdId(String prdId){
		this.prdId = prdId;
	}
	public String getPrdId(){
		return this.prdId;
	}
	public  void setPrdName(String prdName){
		this.prdName = prdName;
	}
	public String getPrdName(){
		return this.prdName;
	}
	public  void setPrdType(String prdType){
		this.prdType = prdType;
	}
	public String getPrdType(){
		return this.prdType;
	}
	public  void setPlatformPrdId(String platformPrdId){
		this.platformPrdId = platformPrdId;
	}
	public String getPlatformPrdId(){
		return this.platformPrdId;
	}
	public  void setVendorId(String vendorId){
		this.vendorId = vendorId;
	}
	public String getVendorId(){
		return this.vendorId;
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
	public  void setState(String state){
		this.state = state;
	}
	public String getState(){
		return this.state;
	}
	public  void setGrdDate(String grdDate){
		this.grdDate = grdDate;
	}
	public String getGrdDate(){
		return this.grdDate;
	}
	public  void setShopId(String shopId){
		this.shopId = shopId;
	}
	public String getShopId(){
		return this.shopId;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
}