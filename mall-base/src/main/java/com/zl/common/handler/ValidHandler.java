package com.zl.common.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import com.zl.common.dto.ResultDto;
import com.zl.common.utils.ResultUtil;

/**
 * 
 * @author coolz
 *
 */
@ControllerAdvice(basePackages = "com.zl")
public class ValidHandler extends ResponseStatusExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ValidHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ResultDto<String> handleControllerException(HttpServletRequest request, Throwable
            ex) {
        ResultDto<String> resultDto = ResultUtil.genenrateByCode(ex.getMessage());
        MethodArgumentNotValidException me = (MethodArgumentNotValidException)ex;
        BindingResult bindingResult = me.getBindingResult();
        List<FieldError> errors = bindingResult.getFieldErrors();
        StringBuilder msg = new StringBuilder();
        for(FieldError fe : errors) {
        	msg.append(fe.getField()).append(":").append(fe.getDefaultMessage()).append(",");
        }
        logger.error("错误信息：{}",   msg.substring(0, msg.lastIndexOf(",")));
        resultDto.setMsg(msg.substring(0, msg.lastIndexOf(",")));
        resultDto.setCode("999999");
        ex.printStackTrace();
        return resultDto;
    }
}
