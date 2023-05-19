package com.zl.mall.user.userinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.user.userinfo.entity.UserInfoEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface UserInfoMapper{
	public List<UserInfoEntity> queryList(Map<String, Object> condtion);
	
	public int add(UserInfoEntity userInfoEntity);
	
	public int update(UserInfoEntity userInfoEntity);
	
	public int delete(@Param("userId") String userId );
}