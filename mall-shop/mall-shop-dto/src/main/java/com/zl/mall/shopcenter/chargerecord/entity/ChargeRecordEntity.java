package com.zl.mall.shopcenter.chargerecord.entity;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author coolz
 *
*/
public class ChargeRecordEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 充值编号
	 *
	 */
	private String id;

	/**
	 *
	 * 充值日期
	 *
	 */
	private String chargeDate;

	/**
	 *
	 * 充值金额
	 *
	 */
	private BigDecimal chargeAmt;

	/**
	 *  店铺id
	 */
	private String shopId;

	public  void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public  void setChargeDate(String chargeDate){
		this.chargeDate = chargeDate;
	}
	public String getChargeDate(){
		return this.chargeDate;
	}
	public  void setChargeAmt(BigDecimal chargeAmt){
		this.chargeAmt = chargeAmt;
	}
	public BigDecimal getChargeAmt(){
		return this.chargeAmt;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

}