package com.zl.mall.shopcenter.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.template.TemplateClient;
import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.base.userrole.UserRoleClient;
import com.zl.mall.base.userrole.entity.UserRoleEntity;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.utils.DateUtils;
import com.zl.mall.common.utils.HttpRequestUtil;
import com.zl.mall.shopcenter.shop.entity.ShopEntity;
import com.zl.mall.shopcenter.shop.mapper.ShopMapper;
import com.zl.mall.shopcenter.shop.service.ShopService;
import com.zl.mall.shopcenter.shopmanager.entity.ShopManagerEntity;
import com.zl.mall.shopcenter.shopmanager.service.ShopManagerService;
import com.zl.mall.user.userauth.entity.UserAuthEntity;

/**
 * 
 * @author coolz
 *
*/
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopMapper shopMapper;

	@Autowired
	private TemplateClient templateClient;

	@Autowired
	private UserRoleClient userRoleClient;

	@Autowired
	private ShopManagerService shopManagerService;

	public List<ShopEntity> queryList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<ShopEntity> list = shopMapper.queryList(queryCondition.getCondition());
		return list;
	}

	@Transactional(rollbackFor = RuntimeException.class)
	public int add(ShopEntity shopEntity) {
		UserAuthEntity user = HttpRequestUtil.getRequestUser();
		String userId = user.getUserId();
		UserRoleEntity userRoleEntity = new UserRoleEntity();
		userRoleEntity.setRoleId("000002");
		userRoleEntity.setUserId(userId);
		userRoleClient.add(userRoleEntity);
		TemplateDto temp = new TemplateDto();
		temp.setName(TempConstant.SHOP_TEMP);
		ResultDto<String> resultDto = templateClient.getSeqno(temp);
		if (resultDto.getData() != null) {
			shopEntity.setShopId(resultDto.getData());
		}
		String currentDate = DateUtils.getCurrentDate();
		shopEntity.setCreatedUserId(userId);
		shopEntity.setCreatedDate(currentDate);
		ShopManagerEntity sme = new ShopManagerEntity();
		sme.setCareatedUserId(userId);
		sme.setCreatedDate(currentDate);
		if (resultDto.getData() != null) {
			sme.setShopId(resultDto.getData());
		}
		sme.setUserId(userId);
		shopManagerService.add(sme);
		return shopMapper.add(shopEntity);
	}

	public int update(ShopEntity shopEntity) {
		return shopMapper.update(shopEntity);
	}

	public int delete(String shopId) {
		return shopMapper.delete(shopId);
	}

	@Override
	public List<ShopEntity> queryAll() {
		UserAuthEntity user = HttpRequestUtil.getRequestUser();
		String userId = user.getUserId();
		Map<String, Object> condition = new HashMap<>(16);
		condition.put("createdUserId", userId);
		List<ShopEntity> list = shopMapper.queryList(condition);
		return list;		
	}
	@Override
	public List<ShopEntity> getShops(String userId){
		List<ShopEntity> list = shopMapper.getShops(userId);
		return list;
	}
}