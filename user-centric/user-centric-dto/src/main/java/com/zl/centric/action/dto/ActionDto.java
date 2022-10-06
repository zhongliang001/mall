package com.zl.centric.action.dto;

import java.io.Serializable;

/**
 * 
 * @author coolz
 *
 */
public class ActionDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2149471363008875128L;

	private String actionId;

	private String actionName;

	private String oper;

	private String menuId;

	private String menuName;

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
	public  void setMenuName(String menuName){
		this.menuName = menuName;
	}
	public String getMenuName(){
		return this.menuName;
	}

}
