package com.zl.mall.base.historyinfo.dto;

import java.io.Serializable;
import java.util.List;

import com.zl.mall.base.historyinfo.entity.HistoryInfoEntity;

/**
 * 
 * @author coolz
 *
 */
public class HistoryInfoDto implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * 主键
	 *
	 */
	private String priKey;

	/**
	 *
	 * 主键值
	 *
	 */
	private String priValue;
	
	private List<HistoryInfoEntity> list;

	public String getPriKey() {
		return priKey;
	}

	public void setPriKey(String priKey) {
		this.priKey = this.priKey == null? priKey: this.priKey + "," + priKey;
	}

	public String getPriValue() {
		return priValue;
	}

	public void setPriValue(String priValue) {
		this.priValue = this.priValue== null ? priValue: this.priValue + "," + priValue;
	}

	public List<HistoryInfoEntity> getList() {
		return list;
	}

	public void setList(List<HistoryInfoEntity> list) {
		this.list = list;
	}
	
	
}
