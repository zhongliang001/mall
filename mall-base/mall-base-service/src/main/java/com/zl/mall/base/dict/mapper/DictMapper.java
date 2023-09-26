package com.zl.mall.base.dict.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.dict.entity.DictEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface DictMapper{
	/**
	 * @param condtion 查询条件
	 * @return 
	 */
	public List<DictEntity> queryList(Map<String, Object> condtion);
	
	/**
	 * 新增字典项
	 * @param dictEntity 字典项信息
	 * @return
	 */
	public int add(DictEntity dictEntity);
	
	/**
	 * 修改字典项
	 * @param dictEntity 字典项信息
	 * @return
	 */
	public int update(DictEntity dictEntity);
	
	/**
	 * 删除字典项
	 * @param dictId 字典id
	 * @return
	 */
	public int delete(@Param("dictId") String dictId );
	
	/**
	 * 查询所有的字典项类型
	 * @return
	 */
	public List<String> selectTypes();
	
	/**
	 * 根据字典类型查询字典项
	 * @param type 字典类型
	 * @return 
	 */
	public List<Map<String, String>> queryByType(String type);
	
	/**
	 * @param condtion 查询条件
	 * @return 
	 */
	public List<DictEntity> queryDictTypeList(Map<String, Object> condtion);
}