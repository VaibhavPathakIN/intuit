package com.order.service;

import java.util.List;
import javax.inject.Inject;

import com.order.dao.OrderMangementDAO;
import com.order.entity.Product;

public class ProductService {

	@Inject
	private OrderMangementDAO dao;

	public Long createProduct(Product productDetails) {
		return dao.save(productDetails);
	}

	public List<Product> getProducts() {
		return (List<Product>) dao.getAll("Product");
	}

	public Product getProductById(Long productId) {
		return (Product) dao.getById(productId, Product.class);
	}
	
	public boolean updateProduct(Product productDetails) {
		return dao.merge(productDetails);
	}

}
