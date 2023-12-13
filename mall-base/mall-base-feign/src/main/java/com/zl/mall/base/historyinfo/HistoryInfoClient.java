package com.zl.mall.base.historyinfo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.mall.base.historyinfo.dto.HistoryInfoDto;
import com.zl.mall.common.dto.ResultDto;

/**
 * 
 * @author coolz
 *
 */
@FeignClient(name = "${service.base}", contextId = "historyinfo")
@RequestMapping("/historyInfo")
public interface HistoryInfoClient {
	@PostMapping("/batchAdd")
	public ResultDto<Integer> batchAdd(@RequestBody HistoryInfoDto historyInfoDto);
}
