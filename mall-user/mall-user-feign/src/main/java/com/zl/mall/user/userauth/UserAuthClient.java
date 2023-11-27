package com.zl.mall.user.userauth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.user.userauth.entity.UserAuthEntity;

/**
 * 
 * @author coolz
 *
 */
@FeignClient(name = "${service.user}")
@RequestMapping("/userAuth")
public interface UserAuthClient {
	/**
	 * 用户登录
	 * @param userName 用户名
	 * @return
	 */
	@GetMapping("/login")
	public ResultDto<UserAuthEntity> login(@RequestParam(value = "userName")  String userName);
}
