package com.zl.mall.shopcenter.orderinfo.dto;

import java.io.Serializable;
import java.util.List;

import com.zl.mall.shopcenter.orderdetail.entity.OrderDetailEntity;
import com.zl.mall.shopcenter.orderinfo.entity.OrderInfoEntity;

/**
 * 
 * @author coolz
 *
 */
public class OrderDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OrderInfoEntity orderInfo;
	
	private List<OrderDetailEntity> orderDetails;

	public OrderInfoEntity getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfoEntity orderInfo) {
		this.orderInfo = orderInfo;
	}

	public List<OrderDetailEntity> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	

}
