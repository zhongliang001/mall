package com.zl.mall.shopcenter.areastatistics.entity;


import java.io.Serializable;

/**
 * @author coolz
 */
public class AreaStatisticsEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 统计编号
     */
    private String id;

    /**
     * 统计日期
     */
    private String statisticsDate;


    /**
     * 省份
     */
    private String area;

    /**
     * 访客数
     */
    private Integer visitors;

    /**
     * 订单数
     */
    private Integer orders;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setStatisticsDate(String statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    public String getStatisticsDate() {
        return this.statisticsDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setVisitors(Integer visitors) {
        this.visitors = visitors;
    }

    public Integer getVisitors() {
        return this.visitors;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Integer getOrders() {
        return this.orders;
    }
}