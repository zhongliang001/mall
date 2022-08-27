package com.zl.common.utils;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zl.common.dto.ResultDto;

/**
 * @author zhongliang
 */
public class ResultUtil {

    @SuppressWarnings("unchecked")
    public static <T> ResultDto<T> genenrate(T t, String msg) {
        ResultDto<T> resultDto = new ResultDto<T>();
        resultDto.setData(t);
        if(t instanceof List){
            PageInfo<T> list = new PageInfo<T>((List<T>) t);
            resultDto.setPageNum(list.getPageNum());
            resultDto.setPageSize(list.getPageSize());
            resultDto.setTotal(list.getTotal());
        }
        resultDto.setMsg(msg);
        return resultDto;
    }

    public static <T> ResultDto<T> genenrateByCode(String msg) {
        ResultDto<T> resultDto = new ResultDto<T>();
        resultDto.setMsg(msg);
        return resultDto;
    }

}
