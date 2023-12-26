package com.zl.mall.base.dict.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.dict.dto.ModDictDto;
import com.zl.mall.base.dict.entity.DictEntity;
import com.zl.mall.base.dict.mapper.DictMapper;
import com.zl.mall.base.dict.service.DictService;
import com.zl.mall.base.template.service.TemplateService;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;

/** 
 * 
 * @author coolz
 *
 */
@Service
public class DictServiceImpl implements DictService {
	@Autowired
	private DictMapper dictMapper;
	
	@Autowired
	private TemplateService templateService;
	
	@Override
	public List<DictEntity> queryList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<DictEntity> list = dictMapper.queryList(queryCondition.getCondition());
		return list;
	}
	
	@CacheEvict(value="dicts",allEntries=true)
	@Override
	public int add(DictEntity dictEntity) {
		String seqno = templateService.getSeqno(TempConstant.DICT_TEMP);
		if(StringUtils.isNotEmpty(seqno)) {
			dictEntity.setDictId(seqno);
		}
		return dictMapper.add(dictEntity);
	}
	
	@Override
	public int update(DictEntity dictEntity) {
		return dictMapper.update(dictEntity);
	}
	
	@Override
	public int delete(String dictId) {
		return dictMapper.delete(dictId);
	}

	@Cacheable(cacheNames="dicts")
	@Override
	public Map<String, List<Map<String, String>>> queryAll() {
		Map<String, List<Map<String, String>>> result = new HashMap<>(16);
		List<String> selectTypes = dictMapper.selectTypes();
		Iterator<String> it = selectTypes.iterator();
		while (it.hasNext()) {
			String type = it.next();
			List<Map<String, String>> dataList = dictMapper.queryByType(type);
			result.put(type, dataList);
		}

		return result;
	}

	@Override
	public List<DictEntity> queryDictTypeList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<DictEntity> list = dictMapper.queryDictTypeList(queryCondition.getCondition());
		return list;
	}
	@CacheEvict(value="dicts",allEntries=true)
	@Override
	public int modDict(ModDictDto modDictDto) {
		String dictType = modDictDto.getDictType();
		String dictTypeName = modDictDto.getDictTypeName();
		List<DictEntity> list = modDictDto.getList();
		int num = 0;
		for (int i = 0; i < list.size(); i++) {
			DictEntity entity = list.get(i);
			String dictId = entity.getDictId();
			if (StringUtils.isEmpty(dictId)) {
				if (StringUtils.isEmpty(entity.getEnName()) || StringUtils.isEmpty(entity.getCnName())) {
					continue;
				}
				String seqno = templateService.getSeqno(TempConstant.DICT_TEMP);
				if(StringUtils.isNotEmpty(seqno)) {
					entity.setDictId(seqno);
				}else {
					entity.setDictId(UUID.randomUUID().toString().replaceAll("-", ""));					
				}
				entity.setDictType(dictType);
				entity.setDictTypeName(dictTypeName);
				num += dictMapper.add(entity);
			} else {
				num += dictMapper.update(entity);
			}
		}
		List<DictEntity> delList = modDictDto.getDelList();
		if (delList != null) {
			for (int i = 0; i < delList.size(); i++) {
				DictEntity dictEntity = delList.get(i);
				if (!StringUtils.isEmpty(dictEntity.getDictId())) {
					num += dictMapper.delete(dictEntity.getDictId());
				}
			}
		}

		return num;
	}
}