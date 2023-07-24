package com.zl.mall.common.utils;

import com.github.pagehelper.Page;
import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.TradeCodeEnum;

/**
 * 
 * @author coolz
 *
 */
public class ResultUtil {

	@SuppressWarnings("unchecked")
	public static <T> ResultDto<T> generate(T t, String tradeCode) {
		ResultDto<T> resultDto = new ResultDto<>();
		resultDto.setData(t);
		if(t instanceof Page) {			
			Page<T> page = (Page<T>)t;
			long total = page.getTotal();
			resultDto.setTotal(total);
		}
		formatResponseInfo(resultDto, tradeCode);
		return resultDto;
	}

	public static <T> ResultDto<T> genenrateByCode(String errDict) {
		ResultDto<T> resultDto = new ResultDto<>();
		formatResponseInfo(resultDto, errDict);
		return resultDto;
	}

	private static <T> void formatResponseInfo(ResultDto<T> resultDto, String errDict) {
		for (TradeCodeEnum s : TradeCodeEnum.values()) {
			if (s.name().equals(errDict)) {
				resultDto.setCode(s.getCode());
				resultDto.setMsg(s.getMessage());
				return;
			}
		}
	}
}
