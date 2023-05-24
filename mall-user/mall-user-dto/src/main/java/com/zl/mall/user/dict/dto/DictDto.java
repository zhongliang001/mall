package com.zl.mall.user.dict.dto;

import java.io.Serializable;

/**
 * 
 * @author coolz
 *
 */
public class DictDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cnName;

	private String enName;

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	@Override
	public String toString() {
		return "DictDto [cnName=" + cnName + ", enName=" + enName + "]";
	}	
	
}
