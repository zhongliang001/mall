package com.zl.mall.shopcenter.vendor.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.shopcenter.vendor.entity.VendorEntity;
/**
 * 
 * @author coolz
 *
*/
@Mapper
public interface VendorMapper{
	
	/**
	 * 查询供应商列表
	 * @param condition 查询条件
	 * @return 供应商列表
	 */
	public List<VendorEntity> queryList(Map<String, Object> condition);
	
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
	public int delete(@Param("vendorId") String vendorId );
}