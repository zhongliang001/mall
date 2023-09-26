package com.zl.mall.common.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.interfaces.Claim;
import com.zl.mall.user.userauth.entity.UserAuthEntity;

/**
 * <p>
 * request 工具
 * </p>
 * 
 * @author zhongliang
 */
public class HttpRequestUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

	private static final String TOKEN_STR = "token";
	
	private static final String NULL_STR = "null";
	/**
	 * 从请求中获取当前请求中获取用户信息
	 * 
	 * @return com.zl.domain.User
	 */
	public static UserAuthEntity getRequestUser() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			if (request.getHeaders(TOKEN_STR).hasMoreElements()) {
				String token = request.getHeaders(TOKEN_STR).nextElement();
				logger.debug("获取到token: {}", token);
				if (StringUtils.isEmpty(token) || NULL_STR.equals(token)) {
					return null;
				}
				Map<String, Claim> parse = JwtUtil.parse(token);
				UserAuthEntity userAuthEntity = new UserAuthEntity();
				Claim claim = parse.get("userId");
				String userId = claim.asString();
				userAuthEntity.setUserId(userId);
				Claim userNameClaim = parse.get("username");
				String userName = userNameClaim.asString();
				userAuthEntity.setUserName(userName);
				return userAuthEntity;
			}
		}
		return null;
	}
}
