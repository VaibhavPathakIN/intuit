package com.order.endpoints;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.order.entity.OrderInfo;
import com.order.entity.OrderSummary;
import com.order.service.OrderService;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

public class OrdersEndpointTest extends JerseyTest {

	@Mock
	private OrderService serviceMock;

	@Override
	protected Application configure() {
		MockitoAnnotations.initMocks(this);

		ResourceConfig config = new ResourceConfig(OrdersEndpoint.class);
		config.register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(serviceMock).to(OrderService.class);
			}
		});
		return config;
	}

	@Test
	public void placeOrderTest() {

		List<OrderSummary> list = new ArrayList<OrderSummary>();
		OrderSummary o = new OrderSummary();
		o.setProductId(1L);
		o.setQuantity(2);
		list.add(o);

		Map<String, Object> map = new HashMap<>();
		map.put("orderDate", new Date());
		map.put("customerId", "acd@gmail.com");
		map.put("orderId", 1L);
		map.put("orderSummary", list);
		map.put("deliveryAddress", "Delhi");

		Mockito.when(serviceMock.placeOrder(Mockito.any(OrderInfo.class))).thenReturn(1L);
		Response output = target("/order/new").request().post(Entity.entity(map, MediaType.APPLICATION_JSON));
		assertEquals(200, output.getStatus());
	}

}
