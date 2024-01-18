package com.zl.mall.shopcenter.areastatistics.service.impl;

import com.github.pagehelper.PageHelper;
import com.zl.mall.base.template.TemplateClient;
import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.common.constant.TempConstant;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.shopcenter.areastatistics.entity.AreaStatisticsEntity;
import com.zl.mall.shopcenter.areastatistics.mapper.AreaStatisticsMapper;
import com.zl.mall.shopcenter.areastatistics.service.AreaStatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author coolz
 */
@Service
public class AreaStatisticsServiceImpl implements AreaStatisticsService {
    private final AreaStatisticsMapper areaStatisticsMapper;
    private final TemplateClient templateClient;

    public AreaStatisticsServiceImpl(AreaStatisticsMapper areaStatisticsMapper, TemplateClient templateClient) {
        Assert.notNull(areaStatisticsMapper, "areaStatisticsMapper must not be null!");
        Assert.notNull(templateClient, "templateClient must not be null!");
        this.areaStatisticsMapper = areaStatisticsMapper;
        this.templateClient = templateClient;
    }

    public List<AreaStatisticsEntity> queryList(QueryCondition queryCondition) {
        PageHelper.startPage(queryCondition.getPageNum(), queryCondition.getPageSize());
        return areaStatisticsMapper.queryList(queryCondition.getCondition());

    }

    public int add(AreaStatisticsEntity areaStatisticsEntity) {
        TemplateDto templateDto = new TemplateDto();
        templateDto.setName(TempConstant.AREA_STAT_TEMP);
        ResultDto<String> seqno = templateClient.getSeqno(templateDto);
        areaStatisticsEntity.setId(seqno.getData());
        return areaStatisticsMapper.add(areaStatisticsEntity);
    }

    public int update(AreaStatisticsEntity areaStatisticsEntity) {
        return areaStatisticsMapper.update(areaStatisticsEntity);
    }

    public int delete(String id) {
        return areaStatisticsMapper.delete(id);
    }
}