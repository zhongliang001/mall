package com.zl.mall.user.userauth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.mall.user.userauth.entity.UserAuthEntity;

/**
 * 
 * @author coolz
 *
 */
@FeignClient(name = "user")
@RequestMapping("/userAuth")
public interface UserAuthClient {
	/**
	 * 用户登录
	 * @param userName 用户名
	 * @return
	 */
	@PostMapping("login")
	public UserAuthEntity login(String userName);
}
