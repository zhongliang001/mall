package com.zl.mall.shopcenter.ztcstatistics.entity;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author coolz
 *
*/
public class ZtcStatisticsEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 主键id
	 *
	 */
	private String id;

	/**
	 *
	 * 产品id
	 *
	 */
	private String prdId;

	/**
	 *
	 * 关键词
	 *
	 */
	private String keyWord;

	/**
	 *
	 * 访问日期
	 *
	 */
	private String statDate;

	/**
	 *
	 * 潜力指数
	 *
	 */
	private Integer potentialIdx;

	/**
	 *
	 * 展现量
	 *
	 */
	private Integer impressions;

	/**
	 *
	 * 点击数
	 *
	 */
	private Integer hits;

	/**
	 *
	 * 点击花费
	 *
	 */
	private BigDecimal cost;

	/**
	 *
	 * 收藏数
	 *
	 */
	private Integer collections;

	/**
	 *
	 * 加购数
	 *
	 */
	private Integer addPurchases;

	/**
	 *
	 * 店铺收藏数
	 *
	 */
	private Integer shopCollections;

	/**
	 *
	 * 支付笔数
	 *
	 */
	private Integer payments;

	/**
	 *
	 * 支付金额
	 *
	 */
	private BigDecimal payAmt;

	/**
	 *
	 * 关键词状态
	 *
	 */
	private String keyState;

	/**
	 *
	 * 商铺id
	 *
	 */
	private String shopId;

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

	public  void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public  void setPrdId(String prdId){
		this.prdId = prdId;
	}
	public String getPrdId(){
		return this.prdId;
	}
	public  void setKeyWord(String keyWord){
		this.keyWord = keyWord;
	}
	public String getKeyWord(){
		return this.keyWord;
	}
	public  void setStatDate(String statDate){
		this.statDate = statDate;
	}
	public String getStatDate(){
		return this.statDate;
	}
	public  void setPotentialIdx(Integer potentialIdx){
		this.potentialIdx = potentialIdx;
	}
	public Integer getPotentialIdx(){
		return this.potentialIdx;
	}
	public  void setImpressions(Integer impressions){
		this.impressions = impressions;
	}
	public Integer getImpressions(){
		return this.impressions;
	}
	public  void setHits(Integer hits){
		this.hits = hits;
	}
	public Integer getHits(){
		return this.hits;
	}
	public  void setCost(BigDecimal cost){
		this.cost = cost;
	}
	public BigDecimal getCost(){
		return this.cost;
	}
	public  void setCollections(Integer collections){
		this.collections = collections;
	}
	public Integer getCollections(){
		return this.collections;
	}
	public  void setAddPurchases(Integer addPurchases){
		this.addPurchases = addPurchases;
	}
	public Integer getAddPurchases(){
		return this.addPurchases;
	}
	public  void setShopCollections(Integer shopCollections){
		this.shopCollections = shopCollections;
	}
	public Integer getShopCollections(){
		return this.shopCollections;
	}
	public  void setPayments(Integer payments){
		this.payments = payments;
	}
	public Integer getPayments(){
		return this.payments;
	}
	public  void setPayAmt(BigDecimal payAmt){
		this.payAmt = payAmt;
	}
	public BigDecimal getPayAmt(){
		return this.payAmt;
	}
	public  void setKeyState(String keyState){
		this.keyState = keyState;
	}
	public String getKeyState(){
		return this.keyState;
	}
	public  void setShopId(String shopId){
		this.shopId = shopId;
	}
	public String getShopId(){
		return this.shopId;
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

	@Override
	public String toString() {
		return "ZtcStatisticsEntity{" +
				"id='" + id + '\'' +
				", prdId='" + prdId + '\'' +
				", keyWord='" + keyWord + '\'' +
				", statDate='" + statDate + '\'' +
				", potentialIdx=" + potentialIdx +
				", impressions=" + impressions +
				", hits=" + hits +
				", cost=" + cost +
				", collections=" + collections +
				", addPurchases=" + addPurchases +
				", shopCollections=" + shopCollections +
				", payments=" + payments +
				", payAmt=" + payAmt +
				", keyState='" + keyState + '\'' +
				", shopId='" + shopId + '\'' +
				", createDate='" + createDate + '\'' +
				", createdUserId='" + createdUserId + '\'' +
				", updateDate='" + updateDate + '\'' +
				", updateUserId='" + updateUserId + '\'' +
				'}';
	}

}