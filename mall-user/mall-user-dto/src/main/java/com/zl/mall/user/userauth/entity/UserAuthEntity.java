package com.zl.mall.user.userauth.entity;


import java.io.Serializable;
/*
 * 
 * @author coolz
 *
*/
public class UserAuthEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId;

	private String userName;

	private String email;

	private String password;

	private String loginTime;

	private String registTime;

	private String loginErrorTime;

	private Integer loginErrorTimes;

	private String userState;

	public  void setUserId(String userId){
		this.userId = userId;
	}
	public String getUserId(){
		return this.userId;
	}
	public  void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return this.userName;
	}
	public  void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public  void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}
	public  void setLoginTime(String loginTime){
		this.loginTime = loginTime;
	}
	public String getLoginTime(){
		return this.loginTime;
	}
	public  void setRegistTime(String registTime){
		this.registTime = registTime;
	}
	public String getRegistTime(){
		return this.registTime;
	}
	public  void setLoginErrorTime(String loginErrorTime){
		this.loginErrorTime = loginErrorTime;
	}
	public String getLoginErrorTime(){
		return this.loginErrorTime;
	}
	public  void setLoginErrorTimes(Integer loginErrorTimes){
		this.loginErrorTimes = loginErrorTimes;
	}
	public Integer getLoginErrorTimes(){
		return this.loginErrorTimes;
	}
	public  void setUserState(String userState){
		this.userState = userState;
	}
	public String getUserState(){
		return this.userState;
	}
}