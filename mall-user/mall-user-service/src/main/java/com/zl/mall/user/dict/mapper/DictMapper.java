package com.zl.mall.user.dict.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.user.dict.entity.DictEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface DictMapper{
	public List<DictEntity> queryList(Map<String, Object> condtion);
	
	public int add(DictEntity dictEntity);
	
	public int update(DictEntity dictEntity);
	
	public int delete(@Param("dictId") String dictId );
}