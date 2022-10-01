package com.zl.common.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

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
        resultDto.setCode("000000");
        resultDto.setMsg(msg);
        return resultDto;
    }

    @SuppressWarnings("unchecked")
    public static <S extends List<?>,T> ResultDto<T> genenrateList(S s, Class<?> target) {
        ResultDto<T> resultDto = new ResultDto<T>();        
        T result = (T) s.stream().map(t -> {
        	Object newInstance = null;
			try {
				newInstance = target.newInstance();
				BeanUtils.copyProperties(t, newInstance);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return newInstance;
        }).collect(Collectors.toList());
        resultDto.setData(result);
        PageInfo<T> list = new PageInfo<T>((List<T>) s);
        resultDto.setPageNum(list.getPageNum());
        resultDto.setPageSize(list.getPageSize());
        resultDto.setTotal(list.getTotal());
        resultDto.setCode("000000");
        return resultDto;
    }

    
    public static <T> ResultDto<T> genenrateByCode(String msg) {
        ResultDto<T> resultDto = new ResultDto<T>();
        resultDto.setMsg(msg);
        return resultDto;
    }

}
