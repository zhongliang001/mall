package com.zl.mall.shopcenter.chargerecord.service.impl;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.template.TemplateClient;
import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.shopcenter.chargerecord.entity.ChargeRecordEntity;
import com.zl.mall.shopcenter.chargerecord.mapper.ChargeRecordMapper;
import com.zl.mall.shopcenter.chargerecord.service.ChargeRecordService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author coolz
 *
*/
@Service
public class ChargeRecordServiceImpl implements ChargeRecordService {
	private final ChargeRecordMapper chargeRecordMapper;

	private final TemplateClient templateClient;

	public ChargeRecordServiceImpl(ChargeRecordMapper chargeRecordMapper, TemplateClient templateClient){
		Assert.notNull(chargeRecordMapper, "ChargeRecordMapper must not be null!");
		Assert.notNull(templateClient, "TemplateClient must not be null!");
		this.chargeRecordMapper = chargeRecordMapper;
		this.templateClient = templateClient;
	}

	public List<ChargeRecordEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		return chargeRecordMapper.queryList(queryCondition.getCondition());
	}
	public int add(ChargeRecordEntity chargeRecordEntity){
		TemplateDto templateDto = new TemplateDto();
		templateDto.setName(TempConstant.CHARGE_TEMP);
		ResultDto<String> seqno = templateClient.getSeqno(templateDto);
		chargeRecordEntity.setId(seqno.getData());
		return chargeRecordMapper.add(chargeRecordEntity);
	}
	public int update(ChargeRecordEntity chargeRecordEntity){
		return chargeRecordMapper.update(chargeRecordEntity);
	}
	public int delete(Map<String, String> map){
		return chargeRecordMapper.delete(map);
	}
}