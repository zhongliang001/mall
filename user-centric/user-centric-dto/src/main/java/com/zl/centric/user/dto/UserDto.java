package com.zl.centric.user.dto;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class UserDto implements Serializable{
	
	private static final long serialVersionUID = -9156853897290387103L;

	private String userId;

	private String userName;

	private String lastLoginTime;

	private String loginErrorTime;

	private Integer loginErrorTimes;

	private String modifyTime;

	private String userStatus;

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
	
	public  void setLastLoginTime(String lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginTime(){
		return this.lastLoginTime;
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
	public  void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}
	public String getModifyTime(){
		return this.modifyTime;
	}
	public  void setUserStatus(String userStatus){
		this.userStatus = userStatus;
	}
	public String getUserStatus(){
		return this.userStatus;
	}
}