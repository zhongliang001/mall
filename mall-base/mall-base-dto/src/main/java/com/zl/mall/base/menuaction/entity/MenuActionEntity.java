package com.zl.mall.base.menuaction.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class MenuActionEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 操作id
	 *
	 */
	private String actionId;

	/**
	 *
	 * 菜单id
	 *
	 */
	private String menuId;

	/**
	 *
	 * 操作编码
	 *
	 */
	private String actionCode;

	/**
	 *
	 * 操作名称
	 *
	 */
	private String actionName;

	/**
	 *
	 * 操作状态：0-未生效；2-生效
	 *
	 */
	private String state;

	public  void setActionId(String actionId){
		this.actionId = actionId;
	}
	public String getActionId(){
		return this.actionId;
	}
	public  void setMenuId(String menuId){
		this.menuId = menuId;
	}
	public String getMenuId(){
		return this.menuId;
	}
	public  void setActionCode(String actionCode){
		this.actionCode = actionCode;
	}
	public String getActionCode(){
		return this.actionCode;
	}
	public  void setActionName(String actionName){
		this.actionName = actionName;
	}
	public String getActionName(){
		return this.actionName;
	}
	public  void setState(String state){
		this.state = state;
	}
	public String getState(){
		return this.state;
	}
}