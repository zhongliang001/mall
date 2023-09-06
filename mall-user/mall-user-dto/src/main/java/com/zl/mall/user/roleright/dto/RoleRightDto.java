package com.zl.mall.user.roleright.dto;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class RoleRightDto implements Serializable{
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
	
	/**
	 * 操作名称
	 */
	private String actionName;	
	
	/**
	 * 角色名
	 */
	private String roleName;
	
	/**
	 * 菜单id
	 */
	private String menuId;
	

	/**
	 * 菜单名
	 */
	private String menuCnName;
	

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
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuCnName() {
		return menuCnName;
	}
	public void setMenuName(String menuCnName) {
		this.menuCnName = menuCnName;
	}
}