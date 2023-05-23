package com.zl.mall.common.dto;

public enum TradeCodeEnum {

    SUCCESS_QUERRY_CODE("000000", "查询成功"),
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
