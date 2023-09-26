package com.zl.mall.base.menuaction.dto;

import java.io.Serializable;
import java.util.List;

import com.zl.mall.base.menuaction.entity.MenuActionEntity;

/**
 * 
 * @author coolz
 *
 */
public class MenuActionDto implements Serializable {

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
	
	/**
	 * 菜单组件
	 */
	private String comp;

	/**
	 * 菜单路径
	 */
	private String path;

	/**
	 * 菜单名
	 */
	private String menuName;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

}
