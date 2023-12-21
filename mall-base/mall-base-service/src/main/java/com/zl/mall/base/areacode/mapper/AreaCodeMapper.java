package com.zl.mall.base.areacode.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.base.areacode.entity.AreaCodeEntity;
import com.zl.mall.common.dto.SelectDto;
/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface AreaCodeMapper{
	/**
	 * 查询省市区编码列表
	 * @param condition 查询条件
	 * @return 返回省市区编码列表
		 */
	public List<AreaCodeEntity> queryList(Map<String, Object> condition);
	
	/**
	 * 新增省市区编码
	 * @param areaCodeEntity 省市区编码信息 
	 * @return 新增省市区编码数量
	 */
	public int add(AreaCodeEntity areaCodeEntity);
	
	/**
	 * 修改省市区编码
	 * @param areaCodeEntity 省市区编码信息 
	 * @return 修改省市区编码数量
	 */
	public int update(AreaCodeEntity areaCodeEntity);
	
	/**
	* 删除省市区编码
	* @param  省市区编码
	* @return 删除省市区编码数量
	*/
	public int delete();

	/**
	 * 查询省份列表
	 * @return 返回省份列表
	 */
	public List<SelectDto> queryProvince();
	
	/**
	 * 查询城市列表
	 * @param  province 省份编码
	 * @return 返回城市列表
	 */
	public List<SelectDto> queryCtiy(@Param("province") String province);
	
	/**
	 * 查询地区列表
	 * @param  city 城市编码
	 * @return 返回地区列表
	 */
	public List<SelectDto> queryArea(@Param("city") String city);
}