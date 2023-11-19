package com.zl.mall.base.template.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zl.mall.base.template.dto.TemplateDto;
import com.zl.mall.base.template.entity.TemplateEntity;
import com.zl.mall.base.template.service.TemplateService;
import com.zl.mall.common.dto.QueryCondition;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.TradeCodeDict;
import com.zl.mall.common.utils.ResultUtil;
/*
 * 
 * @author coolz
 *
*/
@RestController
@RequestMapping("/template")
public class TemplateController {
	@Autowired
	private TemplateService templateService;
	@PostMapping("/")
	public ResultDto<List<TemplateEntity>> queryList(@RequestBody QueryCondition queryCondition){
		List<TemplateEntity> list = templateService.queryList(queryCondition);
		return  ResultUtil.generate(list, TradeCodeDict.SUCCESS_QUERRY_CODE);
	}
	
	@PostMapping("/add")
	public ResultDto<Integer> add(@Validated @RequestBody TemplateEntity templateEntity){
		int num = templateService.add(templateEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_ADD_CODE);
	}
	@PostMapping("/update")
	public ResultDto<Integer> update(@Validated @RequestBody TemplateEntity templateEntity){
		int num = templateService.update(templateEntity);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_UPDATE_CODE);
	}
	@GetMapping("/delete")
	public ResultDto<Integer> delete(@RequestParam(value = "id") String id){
		int num = templateService.delete(id);
		return ResultUtil.generate(num, TradeCodeDict.SUCCESS_DELETE_CODE);
	}
	
	@GetMapping("/getSeqno")
	public ResultDto<String> getSeqno(@Validated @RequestBody TemplateDto templateDto  ){
		
		return ResultUtil.generate("", TradeCodeDict.SUCCESS_DELETE_CODE);
	}
}