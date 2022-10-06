package com.zl.centric.action.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class ActionEntity implements Serializable{
	
	private static final long serialVersionUID = -5930942466950753881L;

	private String actionId;

	private String actionName;

	private String oper;

	private String menuId;

	public  void setActionId(String actionId){
		this.actionId = actionId;
	}
	public String getActionId(){
		return this.actionId;
	}
	public  void setActionName(String actionName){
		this.actionName = actionName;
	}
	public String getActionName(){
		return this.actionName;
	}
	public  void setOper(String oper){
		this.oper = oper;
	}
	public String getOper(){
		return this.oper;
	}
	public  void setMenuId(String menuId){
		this.menuId = menuId;
	}
	public String getMenuId(){
		return this.menuId;
	}
	
	@Override
	public String toString() {
		return "ActionEntity [actionId=" + actionId + ", actionName=" + actionName + ", oper=" + oper + ", menuId="
				+ menuId + "]";
	}
}