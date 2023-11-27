package com.zl.mall.shopcenter.vendor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.zl.mall.base.template.TemplateClient;
import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.utils.DateUtils;
import com.zl.mall.common.utils.HttpRequestUtil;
import com.zl.mall.shopcenter.vendor.entity.VendorEntity;
import com.zl.mall.shopcenter.vendor.mapper.VendorMapper;
import com.zl.mall.shopcenter.vendor.service.VendorService;
import com.zl.mall.user.userauth.entity.UserAuthEntity;
/*
 * 
 * @author coolz
 *
*/
@Service
public class VendorServiceImpl implements VendorService {
	@Autowired
	private VendorMapper vendorMapper;
	
	@Autowired
	private TemplateClient templateClient;
	
	public List<VendorEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<VendorEntity> list = vendorMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(VendorEntity vendorEntity){
		TemplateDto temp = new TemplateDto();
		temp.setName(TempConstant.VENDOR_TEMP);		
		ResultDto<String> dto = templateClient.getSeqno(temp);
		String seqNo = dto.getData();
		vendorEntity.setVendorId(seqNo);
		String currentDate = DateUtils.getCurrentDate();
		vendorEntity.setCreatedDate(currentDate);
		UserAuthEntity requestUser = HttpRequestUtil.getRequestUser();
		String userId = requestUser.getUserId();
		vendorEntity.setCreatedUserId(userId);		
		return vendorMapper.add(vendorEntity);
	}
	public int update(VendorEntity vendorEntity){
		String currentDate = DateUtils.getCurrentDate();
		vendorEntity.setUpdateDate(currentDate);
		UserAuthEntity requestUser = HttpRequestUtil.getRequestUser();
		String userId = requestUser.getUserId();
		vendorEntity.setUpdateUserId(userId);	
		return vendorMapper.update(vendorEntity);
	}
	public int delete(String vendorId){
		return vendorMapper.delete(vendorId);
	}
}