package com.zl.mall.shopcenter.productsku.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.historyinfo.HistoryInfoClient;
import com.zl.mall.base.historyinfo.dto.HistoryInfoDto;
import com.zl.mall.base.historyinfo.entity.HistoryInfoEntity;
import com.zl.mall.base.template.TemplateClient;
import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.utils.ModifyUtil;
import com.zl.mall.shopcenter.productsku.entity.ProductSkuEntity;
import com.zl.mall.shopcenter.productsku.mapper.ProductSkuMapper;
import com.zl.mall.shopcenter.productsku.service.ProductSkuService;

/**
 * 
 * @author coolz
 *
 */
@Service
public class ProductSkuServiceImpl implements ProductSkuService {
	@Autowired
	private ProductSkuMapper productSkuMapper;

	@Autowired
	private TemplateClient templateClient;
	
	@Autowired
	private HistoryInfoClient historyInfoClient;

	public List<ProductSkuEntity> queryList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<ProductSkuEntity> list = productSkuMapper.queryList(queryCondition.getCondition());
		return list;
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

	public int update(ProductSkuEntity productSkuEntity) {
		String skuId = productSkuEntity.getSkuId();
		if(StringUtils.isNotEmpty(skuId)) {
			Map<String, Object> map = new HashMap<>(16);
			map.put("skuId", productSkuEntity.getSkuId());
			List<ProductSkuEntity> queryList = productSkuMapper.queryList(map);
			if(queryList.size() >0) {
				ProductSkuEntity skuEntity = queryList.get(0);
				HistoryInfoDto modifyHis = ModifyUtil.getModifyHis(productSkuEntity, skuEntity);
				List<HistoryInfoEntity> list = modifyHis.getList();
				if(list.size() ==0) {
					return 0;
				}else {
					historyInfoClient.batchAdd(modifyHis);
					return productSkuMapper.update(productSkuEntity);
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
}