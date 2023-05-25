package com.zl.mall.user.menu.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class MenuEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 *
	 * 菜单编号
	 *
	 */
	private String menuId;

	/**
	 *
	 * 菜单中文名
	 *
	 */
	private String menuCnName;

	/**
	 *
	 * 菜单名
	 *
	 */
	private String menuName;

	/**
	 *
	 * 菜单路由
	 *
	 */
	private String path;

	/**
	 *
	 * 上级菜单编号
	 *
	 */
	private String parentId;

	/**
	 *
	 * 菜单状态：0-未上线；2-上线
	 *
	 */
	private String state;

	public  void setMenuId(String menuId){
		this.menuId = menuId;
	}
	public String getMenuId(){
		return this.menuId;
	}
	public  void setMenuCnName(String menuCnName){
		this.menuCnName = menuCnName;
	}
	public String getMenuCnName(){
		return this.menuCnName;
	}
	public  void setMenuName(String menuName){
		this.menuName = menuName;
	}
	public String getMenuName(){
		return this.menuName;
	}
	public  void setPath(String path){
		this.path = path;
	}
	public String getPath(){
		return this.path;
	}
	public  void setParentId(String parentId){
		this.parentId = parentId;
	}
	public String getParentId(){
		return this.parentId;
	}
	public  void setState(String state){
		this.state = state;
	}
	public String getState(){
		return this.state;
	}
}