package com.zl.mall.base.areacode.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zl.mall.base.areacode.entity.AreaCodeEntity;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.SelectDto;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface AreaCodeService{
		/**
	 * 查询省市区编码列表
	 * @param queryCondition 查询条件
	 * @return 返回省市区编码列表
		 */
	public List<AreaCodeEntity> queryList(QueryCondition queryCondition);
	
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
	 * 查询省份信息
	 * @return
	 */
	public List<SelectDto> queryProvince();
	
	/**
	 * 查询省份列表
	 * @return 返回省份列表
	 */
	public List<SelectDto> queryCtiy(String province);
	
	/**
	 * 查询地区列表
	 * @return 返回地区列表
	 */
	public List<SelectDto> queryArea(String city);
}