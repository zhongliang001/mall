package com.zl.mall.base.dict.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class DictEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String dictId;

	private String cnName;

	private String enName;

	private String dictType;

	private String dictTypeName;

	private Integer orderId;

	public  void setDictId(String dictId){
		this.dictId = dictId;
	}
	public String getDictId(){
		return this.dictId;
	}
	public  void setCnName(String cnName){
		this.cnName = cnName;
	}
	public String getCnName(){
		return this.cnName;
	}
	public  void setEnName(String enName){
		this.enName = enName;
	}
	public String getEnName(){
		return this.enName;
	}
	public  void setDictType(String dictType){
		this.dictType = dictType;
	}
	public String getDictType(){
		return this.dictType;
	}
	public  void setDictTypeName(String dictTypeName){
		this.dictTypeName = dictTypeName;
	}
	public String getDictTypeName(){
		return this.dictTypeName;
	}
	public  void setOrderId(Integer orderId){
		this.orderId = orderId;
	}
	public Integer getOrderId(){
		return this.orderId;
	}
}