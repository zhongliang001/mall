package com.zl.mall.shopcenter.productsku.service.impl;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.historyinfo.HistoryInfoClient;
import com.zl.mall.base.historyinfo.dto.HistoryInfoDto;
import com.zl.mall.base.historyinfo.entity.HistoryInfoEntity;
import com.zl.mall.base.template.TemplateClient;
import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.SelectDto;
import com.zl.mall.common.utils.ModifyUtil;
import com.zl.mall.shopcenter.productsku.entity.ProductSkuEntity;
import com.zl.mall.shopcenter.productsku.mapper.ProductSkuMapper;
import com.zl.mall.shopcenter.productsku.service.ProductSkuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author coolz
 *
 */
@Service
public class ProductSkuServiceImpl implements ProductSkuService {
	@Autowired
	private final ProductSkuMapper productSkuMapper;

	@Autowired
	private final TemplateClient templateClient;
	
	@Autowired
	private final HistoryInfoClient historyInfoClient;

	public ProductSkuServiceImpl(ProductSkuMapper productSkuMapper,TemplateClient templateClient,HistoryInfoClient historyInfoClient){
		this.productSkuMapper = productSkuMapper;
		this.templateClient = templateClient;
		this.historyInfoClient = historyInfoClient;
 	}



	public List<ProductSkuEntity> queryList(QueryCondition queryCondition) {
		int size = queryCondition.getPageSize();
		if (size == 0) {
			PageHelper.clearPage();
		}else {
			PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		}
		return productSkuMapper.queryList(queryCondition.getCondition());

	}

	public int add(ProductSkuEntity productSkuEntity) {
		String skuName = productSkuEntity.getSkuName();
		if (StringUtils.isNotEmpty(skuName)) {
			TemplateDto skuTemplateDto = new TemplateDto();
			skuTemplateDto.setName(TempConstant.SKU_TEMP);
			ResultDto<String> skuResult = templateClient.getSeqno(skuTemplateDto);
			String seq = skuResult.getData();
			productSkuEntity.setSkuId(seq);
			
			return productSkuMapper.add(productSkuEntity);
		}
		return 0;
	}

	@Transactional(rollbackFor = RuntimeException.class)
	@CacheEvict(key="#productSkuEntity.prdId", value="productSkus")
	public int update(ProductSkuEntity productSkuEntity) {
		String skuId = productSkuEntity.getSkuId();
		if(StringUtils.isNotEmpty(skuId)) {
			Map<String, Object> map = new HashMap<>(16);
			map.put("skuId", productSkuEntity.getSkuId());
			List<ProductSkuEntity> queryList = productSkuMapper.queryList(map);
			if(!queryList.isEmpty()) {
				ProductSkuEntity skuEntity = queryList.get(0);
				HistoryInfoDto modifyHis = ModifyUtil.getModifyHis(productSkuEntity, skuEntity);
				if (modifyHis != null) {
					List<HistoryInfoEntity> list = modifyHis.getList();
					if(list.isEmpty()) {
						return 0;
					}else {
						historyInfoClient.batchAdd(modifyHis);
						return productSkuMapper.update(productSkuEntity);
					}
				}else {
					return 0;
				}

			}else {
				return 0;
			}
		}else {
			return add(productSkuEntity);
		}
	}

	public int delete(String skuId) {
		return productSkuMapper.delete(skuId);
	}

	@Cacheable(key="#prdId", value="productSkus")
	@Override
	public List<SelectDto> queryForSelect(String prdId) {
		return productSkuMapper.queryForSelect(prdId);
	}
}