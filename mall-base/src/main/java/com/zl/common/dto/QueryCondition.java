package com.zl.common.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>分页查询条件</p>
 * @author zhongliang
 */
public class QueryCondition implements Serializable {

    private Integer pageSize;

    private Integer pageNum;

    private Map<String, Object> condition;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondtion(Map<String, Object> condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "QueryCondition{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", condition=" + condition +
                '}';
    }
}
