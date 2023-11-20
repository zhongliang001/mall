package com.zl.mall.user.userauth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.template.TemplateClient;
import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.user.userauth.dto.UserAuthDto;
import com.zl.mall.user.userauth.dto.UserAuthLogOutDto;
import com.zl.mall.user.userauth.entity.UserAuthEntity;
import com.zl.mall.user.userauth.mapper.UserAuthMapper;
import com.zl.mall.user.userauth.service.UserAuthService;

import feign.template.Template;
/**
 * 
 * @author coolz
 *
*/
@Service
public class UserAuthServiceImpl implements UserAuthService {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private TemplateClient templateClient;
	
	
	@Autowired
	private UserAuthMapper userAuthMapper;
	public List<UserAuthEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<UserAuthEntity> list = userAuthMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(UserAuthEntity userAuthEntity){
		return userAuthMapper.add(userAuthEntity);
	}
	public int update(UserAuthEntity userAuthEntity){
		return userAuthMapper.update(userAuthEntity);
	}
	public int delete(String userId){
		return userAuthMapper.delete(userId);
	}

	@Override
	public UserAuthEntity login(String userName) {
		Map<String, Object> map = new HashMap<String, Object>(16);
		map.put("userName", userName);
		List<UserAuthEntity> queryList = userAuthMapper.queryList(map);		
		return queryList.get(0);
	}

	@Override
	public UserAuthEntity regiter(UserAuthDto userAuthDto) {
		String userName = userAuthDto.getUserName();
		Map<String, Object> map = new HashMap<String, Object>(16);
		map.put("userName", userName);
		List<UserAuthEntity> queryList = userAuthMapper.queryList(map);
		if(queryList.size() > 0) {
			throw new RuntimeException("该用户名已经存在");
		}
		String password = userAuthDto.getPassword();
		String repeatPassword = userAuthDto.getRepeatPassword();
		if(password== null || !password.equals(repeatPassword)) {
			throw new RuntimeException("两次输入的密码不一致");
		}
		UserAuthEntity userAuthEntity = new UserAuthEntity();
		TemplateDto templateDto = new TemplateDto();
		templateDto.setName(TempConstant.USER_TEMP);
		ResultDto<String> result = templateClient.getSeqno(templateDto);
		if(StringUtils.isNotEmpty(result.getData())) {
			userAuthEntity.setUserId(result.getData());
		}else {
			userAuthEntity.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
			
		}
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		userAuthEntity.setPassword(bCryptPasswordEncoder.encode(password));
		userAuthEntity.setEmail(userAuthDto.getEmail());
		userAuthEntity.setUserName(userAuthDto.getUserName());
		userAuthMapper.add(userAuthEntity);
		return userAuthEntity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void logout(UserAuthLogOutDto userAuthLogOutDto) {
		redisTemplate.opsForSet().add(userAuthLogOutDto.getUserId(), userAuthLogOutDto.getToken());
		redisTemplate.expire(userAuthLogOutDto.getUserId(),7200, TimeUnit.SECONDS);
	}
}