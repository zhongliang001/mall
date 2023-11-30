package com.zl.mall.shopcenter.vendor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.zl.mall.common.dto.QueryCondition;

import com.zl.mall.shopcenter.vendor.entity.VendorEntity;
/**
 * 
 * @author coolz
 *
*/
@Service
public interface VendorService{
	
	/**
	 * 查询供应商列表
	 * @param queryCondition 查询条件
	 * @return 供应商列表
	 */
	public List<VendorEntity> queryList(QueryCondition queryCondition);
	
	/**
	 * 新增供应商
	 * @param vendorEntity 供应商信息
	 * @return 新增供应商数量
	 */
	public int add(VendorEntity vendorEntity);
	
	/**
	 * 修改供应商
	 * @param vendorEntity 供应商信息
	 * @return 修改供应商数量
	 */
	public int update(VendorEntity vendorEntity);
	
	/**
	 * 删除供应商
	 * @param vendorId 供应商id
	 * @return 删除供应商数量
	 */
	public int delete(String vendorId);
}