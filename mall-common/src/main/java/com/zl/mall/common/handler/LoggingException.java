package com.zl.mall.common.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LoggingException {
	private final Logger logger = LoggerFactory.getLogger(LoggingException.class);
	 /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public void errorHandler(Exception ex) {
        ex.printStackTrace();
        logger.error("{}",ex.getCause());
     
    }
    
}
