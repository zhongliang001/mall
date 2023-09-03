package com.zl.mall.use.userrole.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class UserRoleEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 用户角色编号
	 *
	 */
	private String userRoleId;

	/**
	 *
	 * 用户id
	 *
	 */
	private String userId;

	/**
	 *
	 * 角色编号
	 *
	 */
	private String roleId;

	public  void setUserRoleId(String userRoleId){
		this.userRoleId = userRoleId;
	}
	public String getUserRoleId(){
		return this.userRoleId;
	}
	public  void setUserId(String userId){
		this.userId = userId;
	}
	public String getUserId(){
		return this.userId;
	}
	public  void setRoleId(String roleId){
		this.roleId = roleId;
	}
	public String getRoleId(){
		return this.roleId;
	}
}