package com.order.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class OrderInfo implements com.order.entity.Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	@ElementCollection(targetClass = OrderSummary.class)
	private List<OrderSummary> orderSummary;
	private String customerId;
	private String deliveryAddress;
	private Date orderDate;
	
	public OrderInfo() {
		this.orderSummary = new ArrayList<OrderSummary>();
	}
	
	public List<OrderSummary> getOrderSummary() {
		return orderSummary;
	}

	public void setOrderSummary(List<OrderSummary> orderSummary) {
		this.orderSummary = orderSummary;
	}

	public Long getOrderId() {
		return orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@PrePersist
	protected void onOrder() {
		orderDate = new Date();
	}

	@Override
	public Long getId() {
		return this.orderId;
	}

}
