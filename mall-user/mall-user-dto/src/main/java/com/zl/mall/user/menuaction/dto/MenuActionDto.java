package com.zl.mall.user.menuaction.dto;

import java.io.Serializable;
import java.util.List;

import com.zl.mall.user.menuaction.entity.MenuActionEntity;

public class MenuActionDto implements Serializable{

	/**
	 * 
	 */
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
	
	private List<MenuActionEntity> list;
	
	private List<MenuActionDto> children;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuCnName() {
		return menuCnName;
	}

	public void setMenuCnName(String menuCnName) {
		this.menuCnName = menuCnName;
	}

	public List<MenuActionEntity> getList() {
		return list;
	}

	public void setList(List<MenuActionEntity> list) {
		this.list = list;
	}

	public List<MenuActionDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuActionDto> children) {
		this.children = children;
	}
	
	
}
