package com.zl.mall.common.dto;

import java.io.Serializable;

/**
 * 
 * @author coolz
 *
 */
public class SelectDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 名字
	 */
	private String name;
	
	/**
	 * 值
	 */
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
