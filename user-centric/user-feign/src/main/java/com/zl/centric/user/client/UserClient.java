package com.zl.centric.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.centric.user.dto.LoginDto;
import com.zl.centric.user.vo.LoginVo;
import com.zl.common.dto.ResultDto;

@FeignClient(name="user-centric-server")
@RequestMapping("/user")
public interface UserClient {
	
	@PostMapping("/login")
	public ResultDto<LoginDto> login(@RequestBody LoginVo loginVo); 

}
