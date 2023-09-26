package com.zl.mall.base.dict.dto;

import java.io.Serializable;
import java.util.List;

import com.zl.mall.base.dict.entity.DictEntity;
/**
 * 
 * @author coolz
 *
 */
public class ModDictDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dictType;
	
	private String dictTypeName;
	
	private List<DictEntity> list;
	
	private List<DictEntity> delList;

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public List<DictEntity> getList() {
		return list;
	}

	public void setList(List<DictEntity> list) {
		this.list = list;
	}

	public List<DictEntity> getDelList() {
		return delList;
	}

	public void setDelList(List<DictEntity> delList) {
		this.delList = delList;
	}
	
	

}
