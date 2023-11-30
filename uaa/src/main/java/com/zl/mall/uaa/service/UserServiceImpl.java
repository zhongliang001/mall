package com.zl.mall.uaa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.shopcenter.shop.ShopClient;
import com.zl.mall.shopcenter.shop.entity.ShopEntity;
import com.zl.mall.user.userauth.UserAuthClient;
import com.zl.mall.user.userauth.dto.UserAuthDetails;
import com.zl.mall.user.userauth.entity.UserAuthEntity;

/**
 * 
 * @author coolz
 *
 */
@Component
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	private UserAuthClient userAuthClient;
	
	@Autowired
	private ShopClient shopClient;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ResultDto<UserAuthEntity> resultDto = userAuthClient.login(username);
		UserAuthEntity userAuthEntity = resultDto.getData();
		if(userAuthEntity != null) {			
			UserAuthDetails userAuthDetails = new UserAuthDetails();
			userAuthDetails.setUserId(userAuthEntity.getUserId());
			userAuthDetails.setPassword(userAuthEntity.getPassword());
			userAuthDetails.setUserName(userAuthEntity.getUserName());
			ResultDto<List<ShopEntity>> shopResult = shopClient.getShops(userAuthEntity.getUserId());
			if(shopResult.getData()!= null) {
				List<ShopEntity> data = shopResult.getData();
				List<Map<String, String>> shops = new ArrayList<>();
				Map<String, String> map = new HashMap<>(16);
				for(int i=0; i < data.size(); i ++) {
					ShopEntity shopEntity = data.get(i);
					map.put("cnName", shopEntity.getShopName());
					map.put("enName", shopEntity.getShopId());
					shops.add(map);
				}
				userAuthDetails.setShops(shops);
			}
			return userAuthDetails;
		}
		return null;
	}

}
