package com.zl.mall.base.areacode.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class AreaCodeEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 省
	 *
	 */
	private String province;

	/**
	 *
	 * 市
	 *
	 */
	private String city;

	/**
	 *
	 * 区
	 *
	 */
	private String area;

	/**
	 *
	 * 名称
	 *
	 */
	private String name;

	public  void setProvince(String province){
		this.province = province;
	}
	public String getProvince(){
		return this.province;
	}
	public  void setCity(String city){
		this.city = city;
	}
	public String getCity(){
		return this.city;
	}
	public  void setArea(String area){
		this.area = area;
	}
	public String getArea(){
		return this.area;
	}
	public  void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
}