package com.zl.mall.common.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页查询条件
 * @author coolz
 *
 */
public class QueryCondition {
	
	/**
	 * 查询条件
	 */
	private Map<String, Object> condition;
	
	/**
	 * 页数
	 */
	private Integer pageNum;
	
	/**
	 * 每页条数
	 */
	private Integer pageSize;
	
	public QueryCondition() {
		this.condition = new HashMap<>();
	}
	
	

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
