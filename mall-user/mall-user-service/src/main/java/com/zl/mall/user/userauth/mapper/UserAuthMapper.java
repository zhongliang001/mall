package com.zl.mall.user.userauth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.user.userauth.entity.UserAuthEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface UserAuthMapper{
	public List<UserAuthEntity> queryList(Map<String, Object> condtion);
	
	public int add(UserAuthEntity userAuthEntity);
	
	public int update(UserAuthEntity userAuthEntity);
	
	public int delete(@Param("userId") String userId );
}