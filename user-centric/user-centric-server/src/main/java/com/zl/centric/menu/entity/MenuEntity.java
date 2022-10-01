package com.zl.centric.menu.entity;


import java.io.Serializable;
/**
 * 
 * @author coolz
 *
*/
public class MenuEntity implements Serializable{
	
	private static final long serialVersionUID = 8918153727348885237L;

	private String menuId;

	private String menuName;

	private String path;

	private String parentId;

	private String filePath;

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
	public  void setFilePath(String filePath){
		this.filePath = filePath;
	}
	public String getFilePath(){
		return this.filePath;
	}
}