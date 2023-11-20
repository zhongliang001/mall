package com.zl.mall.base.template.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.base.sequence.entity.SequenceEntity;
import com.zl.mall.base.sequence.service.SequenceService;
import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.base.template.entity.TemplateEntity;
import com.zl.mall.base.template.mapper.TemplateMapper;
import com.zl.mall.base.template.service.TemplateService;

/*
 * 
 * @author coolz
 *
*/
@Service
public class TemplateServiceImpl implements TemplateService {

	private static final String DATE_TEMP = "D";

	private static final String SEQ_TEMP = "S";

	private static final String DEFAULT_FORMAT = "yyyyMMdd";

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private TemplateMapper templateMapper;

	public List<TemplateEntity> queryList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<TemplateEntity> list = templateMapper.queryList(queryCondition.getCondition());
		return list;
	}

	@Transactional(rollbackFor = RuntimeException.class)
	public int add(TemplateEntity templateEntity) {
		String id = getSeqno("TEMP");
		templateEntity.setId(id);
		String temp = templateEntity.getTemp();
		generateSeq(temp);
		return templateMapper.add(templateEntity);
	}

	private void generateSeq(String temp) {
		String regex = "\\$";
		String[] temps = temp.split(regex);
		for (int num = 0; num < temps.length; num++) {
			String ts = temps[num];
			if (ts.startsWith("{") && ts.endsWith("}")) {
				ts = ts.substring(1, ts.length() - 1);
				if (ts.startsWith(SEQ_TEMP)) {
					String seqInfo = ts.replaceFirst(SEQ_TEMP, "").replace("(", "").replace(")", "");
					String[] seqInfos = seqInfo.split(",");
					String seqName = seqInfos[0];
					int min = Integer.parseInt(seqInfos[1]);
					int max = Integer.parseInt(seqInfos[2]);
					int step = Integer.parseInt(seqInfos[3]);
					int length = Integer.parseInt(seqInfos[4]);
					SequenceEntity sequenceEntity = new SequenceEntity();
					sequenceEntity.setName(seqName);
					sequenceEntity.setMinNo(min);
					sequenceEntity.setStep(step);
					sequenceEntity.setMaxNo(max);
					sequenceEntity.setSeqNo(min);
					sequenceEntity.setLen(length);
					String sequence = getSeqno("SEQ_TEMP");
					sequenceEntity.setSeqId(sequence);
					sequenceService.add(sequenceEntity);
				}
			}
		}
	}

	public int update(TemplateEntity templateEntity) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", templateEntity.getName());
		List<TemplateEntity> list = templateMapper.queryList(map);
		if (list.size() == 0) {
			throw new RuntimeException("被修改的数据不存在");
		} else {
			TemplateEntity te = list.get(0);
			String temp = templateEntity.getTemp();
			String oldTemp = te.getTemp();
			if (!temp.equals(oldTemp)) {
				generateSeq(temp);
			}
		}
		return templateMapper.update(templateEntity);
	}

	public int delete(String id) {
		return templateMapper.delete(id);
	}

	@Override
	public String getSeqno(String name) {
		QueryCondition queryCondition = new QueryCondition();
		Map<String, Object> condition = queryCondition.getCondition();
		condition.put("name", name);
		List<TemplateEntity> list = templateMapper.queryList(condition);
		if (list.size() > 0) {
			TemplateEntity templateEntity = list.get(0);
			String temp = templateEntity.getTemp();
			String regex = "\\$";
			String[] temps = temp.split(regex);
			for (int num = 0; num < temps.length; num++) {
				String ts = temps[num];
				if (ts.startsWith("{") && ts.endsWith("}")) {
					ts = ts.substring(1, ts.length() - 1);
					if (ts.startsWith(DATE_TEMP)) {
						String format = ts.replace(DATE_TEMP, "").replace("(", "").replace(")", "");
						if (StringUtils.isEmpty(format)) {
							format = DEFAULT_FORMAT;
						}
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat(format);
						temps[num] = sdf.format(date);
					} else if (ts.startsWith(SEQ_TEMP)) {
						String seqInfo = ts.replaceFirst(SEQ_TEMP, "").replace("(", "").replace(")", "");
						String[] seqInfos = seqInfo.split(",");
						String seqName = seqInfos[0];
						String sequence = sequenceService.getSequence(seqName);
						temps[num] = sequence;
					}
				}
			}
			String seqNo = "";
			for (int num = 0; num < temps.length; num++) {
				seqNo += temps[num];
			}
			return seqNo;
		}

		return null;
	}
}