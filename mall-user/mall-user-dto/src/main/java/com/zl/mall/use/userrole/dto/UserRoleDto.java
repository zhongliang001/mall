package com.zl.mall.use.userrole.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UserRoleDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	
	private List<Map<String, String>> list;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public List<Map<String, String>> getList() {
		return list;
	}
	
	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}
}
