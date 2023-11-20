package com.zl.mall.base.template.entity;


import java.io.Serializable;

import javax.validation.constraints.NotBlank;
/**
 * 
 * @author coolz
 *
*/
public class TemplateEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 主键id
	 *
	 */
	private String id;

	/**
	 *
	 * 模板名
	 *
	 */
	@NotBlank(message="模板名不能为空")
	private String name;

	/**
	 *
	 * 模板
	 *
	 */
	@NotBlank(message="模板名不能为空")
	private String temp;

	/**
	 *
	 * 模板中文名
	 *
	 */
	private String cnName;

	public  void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public  void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public  void setTemp(String temp){
		this.temp = temp;
	}
	public String getTemp(){
		return this.temp;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
}