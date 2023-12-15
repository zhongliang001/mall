package com.zl.mall.base.areacode.dto;

import java.io.Serializable;

/**
 * 
 * @author coolz
 *
 */
public class AreaCodeDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String area;

	private String name;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

