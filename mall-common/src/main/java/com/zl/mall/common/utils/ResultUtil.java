package com.zl.mall.common.utils;

import com.zl.mall.common.dto.ResultDto;
import com.zl.mall.common.dto.TradeCodeEnum;

public class ResultUtil {

	public static <T> ResultDto<T> generate(T t, String tradeCode) {
		ResultDto<T> resultDto = new ResultDto<>();

		resultDto.setData(t);
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
