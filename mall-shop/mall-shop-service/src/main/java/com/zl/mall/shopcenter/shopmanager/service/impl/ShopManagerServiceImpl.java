package com.zl.mall.shopcenter.shopmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.template.TemplateClient;
import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.shopcenter.shopmanager.entity.ShopManagerEntity;
import com.zl.mall.shopcenter.shopmanager.mapper.ShopManagerMapper;
import com.zl.mall.shopcenter.shopmanager.service.ShopManagerService;
/**
 * 
 * @author coolz
 *
*/
@Service
public class ShopManagerServiceImpl implements ShopManagerService {
	@Autowired
	private ShopManagerMapper shopManagerMapper;
	
	@Autowired
	private TemplateClient templateClient;
	
	public List<ShopManagerEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<ShopManagerEntity> list = shopManagerMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	public int add(ShopManagerEntity shopManagerEntity){
		TemplateDto temp = new TemplateDto();
		temp.setName(TempConstant.MANAGER_TEMP);
		ResultDto<String> result = templateClient.getSeqno(temp);
		shopManagerEntity.setShopManagerId(result.getData());
		return shopManagerMapper.add(shopManagerEntity);
	}
	public int update(ShopManagerEntity shopManagerEntity){
		return shopManagerMapper.update(shopManagerEntity);
	}
	public int delete(String shopManagerId){
		return shopManagerMapper.delete(shopManagerId);
	}
}