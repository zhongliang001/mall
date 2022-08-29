package com.zl.centric.user.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class UserVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8425992185392272019L;
	
	@NotNull(message = "用户名不能为空")
	private String userName;
	@NotNull(message = "密码不能为空")
	private String password;
	@NotNull(message = "新密码不能为空")
	private String newPassword;
	
	public  void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return this.userName;
	}
	public  void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
