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

	@PostMapping("/getSeqno")
	public ResultDto<String> getSeqno(@RequestBody TemplateDto templateDto);
}
