package com.zl.mall.base.template.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.zl.mall.common.dto.QueryCondition;
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
	
	private static final String DEFAULT_FORMAT="yyyyMMdd";
	
	@Autowired
	private TemplateMapper templateMapper;

	public List<TemplateEntity> queryList(QueryCondition queryCondition) {
		PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
		List<TemplateEntity> list = templateMapper.queryList(queryCondition.getCondition());
		return list;
	}

	public int add(TemplateEntity templateEntity) {
		String id = templateEntity.getId();
		if (StringUtils.isEmpty(id)) {
			templateEntity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		return templateMapper.add(templateEntity);
	}

	public int update(TemplateEntity templateEntity) {
		return templateMapper.update(templateEntity);
	}

	public int delete(String id) {
		return templateMapper.delete(id);
	}

	@Override
	public String getSeqno(TemplateDto templateDto) {
		String name = templateDto.getName();
		QueryCondition queryCondition = new QueryCondition();
		Map<String, Object> condition = queryCondition.getCondition();
		condition.put("name", name);
		List<TemplateEntity> list = templateMapper.queryList(condition);
		if (list.size() > 0) {
			TemplateEntity templateEntity = list.get(0);
			String temp = templateEntity.getTemp();
			String regex = "\\$";
			String[] temps = temp.split(regex);
			for(int num=0; num < temps.length; num ++) {
				String ts = temps[num];
				if (ts.startsWith("{") && ts.endsWith("}")) {
					ts = ts.substring(1, ts.length() - 1);
					if (ts.startsWith(DATE_TEMP)) {
						String format = ts.replace(DATE_TEMP, "").replace("(", "").replace(")", "");
						if(StringUtils.isEmpty(format)) {
							format = DEFAULT_FORMAT;							
						}
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat(format);
						temps[num]= sdf.format(date);
					}else if(ts.startsWith(SEQ_TEMP)) {
						String seqInfo = ts.replace(SEQ_TEMP, "").replace("(", "").replace(")", "");
						String[] seqInfos = seqInfo.split(",");
						String seqName = seqInfos[0];
						int min = Integer.parseInt(seqInfos[1]);
						int max = Integer.parseInt(seqInfos[2]);
						int step = Integer.parseInt(seqInfos[3]);
						
						
					}
						
				}
			}
		}

		return null;
	}
}