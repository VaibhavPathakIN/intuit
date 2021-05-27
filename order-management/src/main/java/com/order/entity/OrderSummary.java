package com.order.entity;

import javax.persistence.Embeddable;

@Embeddable
public class OrderSummary {
	
	private Long productId;
	private Integer quantity;

	public Long getProductId() {
		return productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
