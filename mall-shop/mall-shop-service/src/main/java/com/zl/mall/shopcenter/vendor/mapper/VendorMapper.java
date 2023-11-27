package com.zl.mall.shopcenter.vendor.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.mall.shopcenter.vendor.entity.VendorEntity;
/*
 * 
 * @author coolz
 *
*/
@Mapper
public interface VendorMapper{
	public List<VendorEntity> queryList(Map<String, Object> condition);
	
	public int add(VendorEntity vendorEntity);
	
	public int update(VendorEntity vendorEntity);
	
	public int delete(@Param("vendorId") String vendorId );
}