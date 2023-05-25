package com.zl.mall.common.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zl.mall.common.utils.HttpRequestUtil;
import com.zl.mall.user.userauth.entity.UserAuthEntity;

/**
 * @author zhongliang
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

    /**
     * 会话ID
      */
    private final static String SESSION_ID = "sessionId";

    /**
     * 用户id
     */
    private final static String USER_ID = "userId";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        String logid = httpServletRequest.getHeader("logid");
        if(StringUtils.isEmpty(logid)){
            logid = UUID.randomUUID().toString().replaceAll("-", "");
            httpServletRequest.setAttribute("logid", logid);
        }
        MDC.put(SESSION_ID, logid);
        UserAuthEntity requestUser = HttpRequestUtil.getRequestUser();
        if (requestUser != null) {
            MDC.put(USER_ID, requestUser.getUserId());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
        MDC.remove(SESSION_ID);

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
    }
}
