package com.zl.mall.common.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * 
 * @author coolz
 *
 */
@Component
public class FeignConfiguration implements RequestInterceptor {
	@Override
	public void apply(RequestTemplate requestTemplate) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (null != attributes) {
			HttpServletRequest request = attributes.getRequest();
			String logid = (String) request.getAttribute("logid");
			requestTemplate.header("logid", logid);
		}
	}
}
