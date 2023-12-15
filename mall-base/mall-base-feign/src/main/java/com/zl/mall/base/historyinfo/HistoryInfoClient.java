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
	/**
	 * 新增历史记录
	 * @param historyInfoDto 历史记录信息
	 * @return 返回新增记录数
	 */
	@PostMapping("/batchAdd")
	public ResultDto<Integer> batchAdd(@RequestBody HistoryInfoDto historyInfoDto);
}
