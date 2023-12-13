package com.zl.mall.base.historyinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.base.historyinfo.dto.HistoryInfoDto;
import com.zl.mall.base.historyinfo.entity.HistoryInfoEntity;
import com.zl.mall.base.historyinfo.mapper.HistoryInfoMapper;
import com.zl.mall.base.historyinfo.service.HistoryInfoService;
import com.zl.mall.base.template.service.TemplateService;
/**
 * 
 * @author coolz
 *
*/
@Service
public class HistoryInfoServiceImpl implements HistoryInfoService {
	@Autowired
	private HistoryInfoMapper historyInfoMapper;
	
	@Autowired
	private TemplateService templateService;
public List<HistoryInfoEntity> queryList(QueryCondition queryCondition){
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<HistoryInfoEntity> list = historyInfoMapper.queryList(queryCondition.getCondition());
		return list;
	}
	public int add(HistoryInfoEntity historyInfoEntity){
		return historyInfoMapper.add(historyInfoEntity);
	}
	public int update(HistoryInfoEntity historyInfoEntity){
		return historyInfoMapper.update(historyInfoEntity);
	}
	public int delete(String id){
		return historyInfoMapper.delete(id);
	}
	@Override
	public int batchAdd(HistoryInfoDto historyInfoDto) {
		String priKey = historyInfoDto.getPriKey();
		String priValue = historyInfoDto.getPriValue();
		List<HistoryInfoEntity> list = historyInfoDto.getList();
		if(list == null ||list.size() ==0) {
			return 0;
		}
		for(int index = 0;index < list.size(); index++) {
			HistoryInfoEntity historyInfoEntity = list.get(index);
			String seqno = templateService.getSeqno(TempConstant.HIS_TEMP);
			historyInfoEntity.setId(seqno);
			historyInfoEntity.setPriKey(priKey);
			historyInfoEntity.setPriValue(priValue);
		}
		return historyInfoMapper.batchAdd(list);
	}
}