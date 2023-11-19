package com.zl.mall.base.template.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class TemplateDto implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="模板名不能为空")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
