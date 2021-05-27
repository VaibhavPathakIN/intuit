package com.order.service;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.order.dao.OrderMangementDAO;
import com.order.entity.OrderInfo;
import com.order.entity.OrderSummary;
import com.order.entity.Product;
import com.order.exceptions.WebExceptions;

public class OrderService {

	@Inject
	private OrderMangementDAO dao;

	public Long placeOrder(OrderInfo orderDetails) {

		synchronized (dao) {
			List<String> unavailableProducts = new ArrayList<String>();
			List<Product> orderedProducts = new ArrayList<Product>();

			validateOrder(orderedProducts, unavailableProducts, orderDetails);

			if (unavailableProducts.size() > 0) {
				throw new WebExceptions("Product(s) " + unavailableProducts + " is/are not available as required")
						.InternalException();
			}

			// generating invoice
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				throw new WebExceptions(e.getMessage()).InternalException();
			}

			Long orderId = dao.save(orderDetails);

			for (Product p : orderedProducts) {
				dao.merge(p);
			}

			return orderId;
		}
	}

	private void validateOrder(List<Product> orderedProducts, List<String> unavailableProducts,
			OrderInfo orderDetails) {
		
		for (OrderSummary o : orderDetails.getOrderSummary()) {
			Product product = (Product) dao.getById(o.getProductId(), Product.class);

			if (product == null)
				throw new WebExceptions("Invalid product listed in order").InvalidRequestException();

			if (product.getQuantity() < o.getQuantity()) {
				unavailableProducts.add(product.getProductName());
			}

			product.setQuantity(product.getQuantity() - o.getQuantity());
			orderedProducts.add(product);
		}
	}

}
