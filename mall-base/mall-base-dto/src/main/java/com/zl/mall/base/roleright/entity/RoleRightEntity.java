package com.zl.mall.base.roleright.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class RoleRightEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 权限编号
	 *
	 */
	private String rightId;

	/**
	 *
	 * 角色编号
	 *
	 */
	private String roleId;

	/**
	 *
	 * 操作id
	 *
	 */
	private String actionId;

	public  void setRightId(String rightId){
		this.rightId = rightId;
	}
	public String getRightId(){
		return this.rightId;
	}
	public  void setRoleId(String roleId){
		this.roleId = roleId;
	}
	public String getRoleId(){
		return this.roleId;
	}
	public  void setActionId(String actionId){
		this.actionId = actionId;
	}
	public String getActionId(){
		return this.actionId;
	}
}