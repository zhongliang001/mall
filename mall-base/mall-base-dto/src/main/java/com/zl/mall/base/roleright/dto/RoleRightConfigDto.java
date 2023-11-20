package com.zl.mall.base.roleright.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author coolz
 *
 */
public class RoleRightConfigDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String roleId;
	
	private List<String> list;
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
}
