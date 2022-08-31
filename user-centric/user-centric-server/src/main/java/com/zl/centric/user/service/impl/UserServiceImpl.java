package com.zl.centric.user.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.centric.user.entity.UserEntity;
import com.zl.centric.user.mapper.UserMapper;
import com.zl.centric.user.service.UserService;
import com.zl.centric.user.vo.LoginVo;
import com.zl.common.dto.QueryCondition;
import com.zl.common.exception.ZlException;
/**
 * 
 * @author coolz
 *
*/
@Service
public class UserServiceImpl implements UserService {
	
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userDao;
	
	@Override
	public List<UserEntity> queryList(QueryCondition queryCondition){		
		List<UserEntity> list = userDao.queryList(queryCondition.getCondition());
		return list;
	}
	
	@Override
	public int add(UserEntity userEntity){
		return userDao.add(userEntity);
	}
	
	@Override
	public int update(UserEntity userEntity){
		return userDao.update(userEntity);
	}
	
	@Override
	public int delete(String userId){
		return userDao.delete(userId);
	}

	@Override
	public UserEntity login(LoginVo loginVo) throws ZlException{
		String userName = loginVo.getUserName();
		logger.info("用户{}尝试登录系统：", userName);
		String password = loginVo.getPassword();
		Map<String, Object> condition = new HashMap<>();
		condition.put("userName", userName);
		List<UserEntity> queryList = userDao.queryList(condition);
		if(queryList.size() > 0) {
			UserEntity userEntity = queryList.get(0);			
			userDao.update(userEntity);
			String pwd = userEntity.getPassword();
			if(pwd != null && pwd.equals(password)) {
				Date date = new Date();;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				userEntity.setLoginErrorTime(sdf.format(date));
				userEntity.setLoginErrorTimes(userEntity.getLoginErrorTimes() + 1);
				userDao.update(userEntity);
				logger.info("用户{}登录失败,用户名或者密码错误", userName);
				throw  new ZlException("用户名或者密码错误");
			}else {
				Date date = new Date();;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				userEntity.setLastLoginTime(sdf.format(date));
				userDao.update(userEntity);
				logger.info("用户{}登录成功!", userName);
				return userEntity;
			}
		}else {
			throw  new ZlException("用户名或者密码错误!");
		}
		
	}
}