package com.zl.mall.base.template.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

/**
 * 
 * @author coolz
 *
 */
public class TemplateDto implements Serializable {	
	
	/**
	 * serial
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 模板名
	 */
	@NotBlank(message="模板名不能为空")
	private String name;
	
	/**
	 * 获取模板名
	 */	
	public String getName() {
		return name;
	}
	
	/**
	 * 设置模板名
	 */
	public void setName(String name) {
		this.name = name;
	}
	

}
