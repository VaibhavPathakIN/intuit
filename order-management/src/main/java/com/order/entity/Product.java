package com.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product implements com.order.entity.Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;

	@Column(unique = true)
	private String productName;
	private String modelNo;
	private Double price;
	private Integer quantity;

	public Long getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getModelNo() {
		return modelNo;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public Long getId() {
		return this.productId;
	}

}
