package com.zl.mall.base.template.entity;


import com.zl.mall.common.annotation.Column;
import com.zl.mall.common.annotation.PrimaryKey;
import com.zl.mall.common.annotation.Record;
import com.zl.mall.common.annotation.Table;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
@Table("template")
public class TemplateEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 主键id
	 *
	 */
	@PrimaryKey
	@Column(name = "id", comment = "模板id")
	private String id;

	/**
	 *
	 * 模板名
	 *
	 */
	@NotBlank(message="模板名不能为空")
	@Record
	@Column(name = "name", comment = "模板名")
	private String name;

	/**
	 *
	 * 模板
	 *
	 */
	@NotBlank(message="模板名不能为空")
	@Record
	@Column(name = "temp", comment = "模板")
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