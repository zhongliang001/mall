package com.zl.centric.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.centric.user.dto.LoginDto;
import com.zl.centric.user.dto.UserDto;
import com.zl.common.dto.ResultDto;
/**
 * 
 * @author coolz
 *
 */
@FeignClient(name="user-centric-server")
@RequestMapping("/user")
public interface UserClient {
	
	/**
	 * 登录
	 * @param loginVo 登录对象
	 * @throws ZlException 登录失败异常
	 * @return 返回用户信息
	 */
	@PostMapping("/login")
	public ResultDto<UserDto> login(@RequestBody LoginDto loginDto); 

}
