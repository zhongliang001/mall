package com.zl.mall.base.areacode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.areacode.entity.AreaCodeEntity;
import com.zl.mall.base.areacode.mapper.AreaCodeMapper;
import com.zl.mall.base.areacode.service.AreaCodeService;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.SelectDto;
/**
 * 
 * @author coolz
 *
*/
@Service
public class AreaCodeServiceImpl implements AreaCodeService {
	@Autowired
	private AreaCodeMapper areaCodeMapper;
	public List<AreaCodeEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<AreaCodeEntity> list = areaCodeMapper.queryList(queryCondition.getCondition());
		return list;
	}
	public int add(AreaCodeEntity areaCodeEntity){
		return areaCodeMapper.add(areaCodeEntity);
	}
	public int update(AreaCodeEntity areaCodeEntity){
		return areaCodeMapper.update(areaCodeEntity);
	}
	public int delete(){
		return areaCodeMapper.delete();
	}
	@Override
	public List<SelectDto> queryProvince() {
		return areaCodeMapper.queryProvince();
	}
	@Override
	public List<SelectDto> queryCtiy(String province) {
		return areaCodeMapper.queryCtiy(province);
	}
	@Override
	public List<SelectDto> queryArea(String city) {
		return areaCodeMapper.queryArea(city);
	}
}