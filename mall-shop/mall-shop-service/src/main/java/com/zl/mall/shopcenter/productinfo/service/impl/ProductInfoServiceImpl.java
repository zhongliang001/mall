package com.zl.mall.shopcenter.productinfo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.historyinfo.HistoryInfoClient;
import com.zl.mall.base.historyinfo.dto.HistoryInfoDto;
import com.zl.mall.base.template.TemplateClient;
import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.SelectDto;
import com.zl.mall.common.utils.DateUtils;
import com.zl.mall.common.utils.HttpRequestUtil;
import com.zl.mall.common.utils.ModifyUtil;
import com.zl.mall.shopcenter.productinfo.dto.ProductInfoDto;
import com.zl.mall.shopcenter.productinfo.entity.ProductInfoEntity;
import com.zl.mall.shopcenter.productinfo.mapper.ProductInfoMapper;
import com.zl.mall.shopcenter.productinfo.service.ProductInfoService;
import com.zl.mall.shopcenter.productsku.entity.ProductSkuEntity;
import com.zl.mall.shopcenter.productsku.mapper.ProductSkuMapper;
import com.zl.mall.shopcenter.productsku.service.ProductSkuService;
import com.zl.mall.user.userauth.entity.UserAuthEntity;

/**
 * 
 * @author coolz
 *
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
	@Autowired
	private ProductInfoMapper productInfoMapper;

	@Autowired
	private TemplateClient templateClient;

	@Autowired
	private ProductSkuService productSkuService;

	@Autowired
	private HistoryInfoClient historyInfoClient;
	
	@Autowired
	private ProductSkuMapper productSkuMapper;

	public List<ProductInfoEntity> queryList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<ProductInfoEntity> list = productInfoMapper.queryList(queryCondition.getCondition());
		return list;
	}

	public int add(ProductInfoEntity productInfoEntity) {
		return productInfoMapper.add(productInfoEntity);
	}

	@Transactional(rollbackFor = RuntimeException.class)
	public int update(ProductInfoEntity productInfoEntity) {
		String prdId = productInfoEntity.getPrdId();
		Map<String, Object> map = new HashMap<>(16);
		map.put("prdId", prdId);
		List<ProductInfoEntity> queryList = productInfoMapper.queryList(map);
		if (queryList.size() != 0) {
			ProductInfoEntity prd = queryList.get(0);
			HistoryInfoDto modifyHis = ModifyUtil.getModifyHis(productInfoEntity, prd);
			if (modifyHis != null) {
				historyInfoClient.batchAdd(modifyHis);
			}
		}
		UserAuthEntity requestUser = HttpRequestUtil.getRequestUser();
		productInfoEntity.setUpdateDate(DateUtils.getCurrentDate());
		if (requestUser != null) {
			productInfoEntity.setUpdateUserId(requestUser.getUserId());
		}
		return productInfoMapper.update(productInfoEntity);
	}
	
	@CacheEvict(key="#prdId", value="produsts")
	public int delete(String prdId) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("prdId", prdId);
		List<ProductInfoEntity> list = productInfoMapper.queryList(map);
		if(list.size() > 0) {
			ProductInfoDto pd = new ProductInfoDto();
			ProductInfoEntity productInfoEntity = list.get(0);
			productInfoEntity.setState("2");
			pd.setProductInfo(productInfoEntity);
			List<ProductSkuEntity> pses = productSkuMapper.queryList(map);
			if(pses.size() > 0) {
				pd.setDelList(pses);
			}
			return updateProduct(pd);
			
		}
		return 0;
	}

	@CacheEvict(key="#productInfoDto.productInfo.shopId", value="products")
	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public int addProduct(ProductInfoDto productInfoDto) {
		int resultNum = 0;
		TemplateDto templateDto = new TemplateDto();
		templateDto.setName(TempConstant.PRODUCT_TEMP);
		ResultDto<String> result = templateClient.getSeqno(templateDto);
		String seqNo = result.getData();

		ProductInfoEntity productInfo = productInfoDto.getProductInfo();
		productInfo.setPrdId(seqNo);
		productInfo.setCreateDate(DateUtils.getCurrentDate());
		UserAuthEntity requestUser = HttpRequestUtil.getRequestUser();
		String userId = requestUser.getUserId();
		productInfo.setCreatedUserId(userId);
		resultNum = productInfoMapper.add(productInfo);

		List<ProductSkuEntity> list = productInfoDto.getList();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ProductSkuEntity productSkuEntity = list.get(i);
				productSkuEntity.setPrdId(seqNo);
				productSkuService.add(productSkuEntity);
			}
		}

		return resultNum;
	}

	@Override
	public int updateProduct(ProductInfoDto productInfoDto) {
		ProductInfoEntity productInfo = productInfoDto.getProductInfo();
		int update = update(productInfo);
		List<ProductSkuEntity> list = productInfoDto.getList();
		int num = 0;
		if (list != null &&list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ProductSkuEntity productSkuEntity = list.get(i);
				productSkuEntity.setPrdId(productInfo.getPrdId());
				num += productSkuService.update(productSkuEntity);
			}
		}
		List<ProductSkuEntity> delList = productInfoDto.getDelList();
		if (delList.size() > 0) {
			for (int i = 0; i < delList.size(); i++) {
				ProductSkuEntity productSkuEntity = delList.get(i);
				String skuId = productSkuEntity.getSkuId();
				if(StringUtils.isNotEmpty(skuId)) {
					productSkuEntity.setPrdId(productInfo.getPrdId());
					productSkuEntity.setState("1");
					num += productSkuService.update(productSkuEntity);
				}
				
			}
		}
		return num + update;
	}

	@Cacheable(key="#shopId", value="products")
	@Override
	public List<SelectDto> queryForSelect(String shopId) {
		return  productInfoMapper.queryForSelect(shopId);
	}
}