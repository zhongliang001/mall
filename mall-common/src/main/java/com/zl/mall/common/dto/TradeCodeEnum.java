package com.zl.mall.common.dto;

/**
 * 交易码字典
 * @author coolz
 *
 */
public enum TradeCodeEnum {
	// 交易成功
    SUCCESS_QUERRY_CODE("000000", "查询成功"), 
    // 新增成功
    SUCCESS_ADD_CODE("000001", "新增成功"),
    // 修改成功
    SUCCESS_UPDATE_CODE("000002", "修改成功"),   
    // 删除成功
    SUCCESS_DELETE_CODE("000003", "删除成功"),
    // 未授权
	  NO_AUTH("111111", "未授权");
    
    private String code;

    private String message;

    TradeCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
	
}
