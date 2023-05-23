package com.zl.mall.user.userinfo.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class UserInfoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId;

	private String userName;

	private String nickName;

	private String certType;

	private String certCode;

	private String phone;

	private String sex;

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
	public  void setNickName(String nickName){
		this.nickName = nickName;
	}
	public String getNickName(){
		return this.nickName;
	}
	public  void setCertType(String certType){
		this.certType = certType;
	}
	public String getCertType(){
		return this.certType;
	}
	public  void setCertCode(String certCode){
		this.certCode = certCode;
	}
	public String getCertCode(){
		return this.certCode;
	}
	public  void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public  void setSex(String sex){
		this.sex = sex;
	}
	public String getSex(){
		return this.sex;
	}
}