package com.zl.mall.shopcenter.vendor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.zl.mall.common.dto.QueryCondition;

import com.zl.mall.shopcenter.vendor.entity.VendorEntity;
/*
 * 
 * @author coolz
 *
*/
@Service
public interface VendorService{
	public List<VendorEntity> queryList(QueryCondition queryCondition);
	
	public int add(VendorEntity vendorEntity);
	
	public int update(VendorEntity vendorEntity);
	
	public int delete(String vendorId);
}