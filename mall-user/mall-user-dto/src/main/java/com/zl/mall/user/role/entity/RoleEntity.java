package com.zl.mall.user.role.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class RoleEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 角色编号
	 *
	 */
	private String roleId;

	/**
	 *
	 * 角色名
	 *
	 */
	private String roleName;

	/**
	 *
	 * 备注
	 *
	 */
	private String remark;

	/**
	 *
	 * 角色状态：0-未上线；2-上线
	 *
	 */
	private String state;

	public  void setRoleId(String roleId){
		this.roleId = roleId;
	}
	public String getRoleId(){
		return this.roleId;
	}
	public  void setRoleName(String roleName){
		this.roleName = roleName;
	}
	public String getRoleName(){
		return this.roleName;
	}
	public  void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}
	public  void setState(String state){
		this.state = state;
	}
	public String getState(){
		return this.state;
	}
}