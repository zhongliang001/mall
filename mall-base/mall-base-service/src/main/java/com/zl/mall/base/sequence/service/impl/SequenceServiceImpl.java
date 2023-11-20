package com.zl.mall.base.sequence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.sequence.entity.SequenceEntity;
import com.zl.mall.base.sequence.mapper.SequenceMapper;
import com.zl.mall.base.sequence.service.SequenceService;
import com.zl.mall.common.dto.QueryCondition;

/*
 * 
 * @author coolz
 *
*/
@Service
public class SequenceServiceImpl implements SequenceService {
	@Autowired
	private SequenceMapper sequenceMapper;

	public List<SequenceEntity> queryList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<SequenceEntity> list = sequenceMapper.queryList(queryCondition.getCondition());
		return list;
	}

	public int add(SequenceEntity sequenceEntity) {
		String name = sequenceEntity.getName();
		QueryCondition queryCondition = new QueryCondition();
		queryCondition.getCondition().put("name", name);
		List<SequenceEntity> list = sequenceMapper.queryList(queryCondition.getCondition());
		if (list.size() == 0) {
			return sequenceMapper.add(sequenceEntity);
		} else {
			SequenceEntity se = list.get(0);
			sequenceEntity.setVersion(se.getVersion());
			return sequenceMapper.update(sequenceEntity);
		}
	}

	public int update(SequenceEntity sequenceEntity) {
		return sequenceMapper.update(sequenceEntity);
	}

	public int delete(String seqId) {
		return sequenceMapper.delete(seqId);
	}

	@Override
	public String getSequence(String seqName) {
		QueryCondition queryCondition = new QueryCondition();
		queryCondition.getCondition().put("name", seqName);
		List<SequenceEntity> list = sequenceMapper.queryList(queryCondition.getCondition());
		if (list.size() == 0) {
			return "";
		} else {
			SequenceEntity sequenceEntity = list.get(0);
			Integer seqNo = sequenceEntity.getSeqNo();
			Integer step = sequenceEntity.getStep();
			Integer newSeqNo = seqNo + step;
			Integer maxNo = sequenceEntity.getMaxNo();
			Integer minNo = sequenceEntity.getMinNo();
			if (newSeqNo.compareTo(maxNo) > 0) {
				newSeqNo = minNo;
			}
			sequenceEntity.setSeqNo(newSeqNo);
			sequenceMapper.update(sequenceEntity);
			String seqNoStr = String.valueOf(newSeqNo);
			Integer len = sequenceEntity.getLen();
			while (seqNoStr.length() < len) {
				seqNoStr = "0" + seqNoStr;
			}
			return seqNoStr;

		}
	}
}