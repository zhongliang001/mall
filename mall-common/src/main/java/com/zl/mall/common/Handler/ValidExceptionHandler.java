package com.zl.mall.common.Handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zl.mall.common.dto.ResultDto;

@ControllerAdvice
public class ValidExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(ValidExceptionHandler.class);

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResultDto<String> handleMethodArgumentNotValidException(Exception exception) {
		BindingResult bindingResult = null;
		ResultDto<String> result = new ResultDto<String>();
		if (exception instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException me = (MethodArgumentNotValidException) exception;
			bindingResult = me.getBindingResult();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			String msg = "";
			int i = 0;
			for (FieldError f : fieldErrors) {
				if (i != 0) {
					msg += ",";
				}
				Object[] arguments = f.getArguments();
				DefaultMessageSourceResolvable de = (DefaultMessageSourceResolvable) arguments[0];
				String defaultMessage = f.getDefaultMessage();
				msg += de.getDefaultMessage() + ":" + defaultMessage;
				i++;
			}
			logger.info("错误信息{}", msg);
			result.setCode("999999");
			result.setMsg(msg);
		}
		return result;
	}

}
