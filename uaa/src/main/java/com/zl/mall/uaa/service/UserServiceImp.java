package com.zl.mall.uaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.user.userauth.UserAuthClient;
import com.zl.mall.user.userauth.dto.UserAuthDetails;
import com.zl.mall.user.userauth.entity.UserAuthEntity;
@Component
public class UserServiceImp implements UserDetailsService{

	@Autowired
	private UserAuthClient userAuthClient;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ResultDto<UserAuthEntity> resultDto = userAuthClient.login(username);
		UserAuthEntity userAuthEntity = resultDto.getData();
		if(userAuthEntity != null) {			
			UserAuthDetails userAuthDetails = new UserAuthDetails();
			userAuthDetails.setUserId(userAuthEntity.getUserId());
			userAuthDetails.setPassword(userAuthEntity.getPassword());
			userAuthDetails.setUserName(userAuthEntity.getUserName());
			return userAuthDetails;
		}
		return null;
	}

}
