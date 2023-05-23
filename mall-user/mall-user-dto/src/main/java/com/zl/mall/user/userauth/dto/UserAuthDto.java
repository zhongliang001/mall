package com.zl.mall.user.userauth.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

/**
 * 
 * @author coolz
 *
 */
public class UserAuthDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;

	@NotBlank
	private String userName;

	private String email;
	@NotBlank
	private String password;
	@NotBlank
	private String repeatPassword;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

}
