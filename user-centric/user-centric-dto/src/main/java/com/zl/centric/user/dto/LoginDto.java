package com.zl.centric.user.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author coolz
 *
 */
public class LoginDto implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6712944942455664728L;
	@NotNull(message = "用户名不能为空")
	private String userName;
	@NotNull(message = "密码不能为空")
	private String password;
	
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

}
