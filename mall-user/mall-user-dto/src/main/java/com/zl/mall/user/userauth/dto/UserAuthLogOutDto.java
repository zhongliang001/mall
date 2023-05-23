package com.zl.mall.user.userauth.dto;

import java.io.Serializable;

/**
 * 
 * @author coolz
 *
 */
public class UserAuthLogOutDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	
	private String token;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
