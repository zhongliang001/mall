package com.zl.mall.base.template;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.common.dto.ResultDto;

/**
 * 
 * @author coolz
 *
 */
@FeignClient(name = "${service.base}", contextId="template")
@RequestMapping("/template")
public interface TemplateClient {

	/**
	 * 获取流水号
	 * @param templateDto 模板dto
	 * @return 返回流水号
	 */
	@PostMapping("/getSeqno")
	public ResultDto<String> getSeqno(@RequestBody TemplateDto templateDto);
}
