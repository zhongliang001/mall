package com.zl.mall.common.dto;

/**
 * 封装返回数据dto
 * @author coolz
 *
 * @param <T>
 */
public class ResultDto<T> {
	
	/**
	 * 封装返回数据
	 */
	private T data;
	
	/**
	 * 总记录数
	 */
	private Long total;
	
	/**
	 * 返回请求结果码
	 */
	String code;

	/**
	 * 返回请求结果信息
	 */
	String msg;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
