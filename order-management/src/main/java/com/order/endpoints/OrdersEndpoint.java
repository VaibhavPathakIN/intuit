package com.order.endpoints;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.order.entity.OrderInfo;
import com.order.exceptions.WebExceptions;
import com.order.service.OrderService;

@Path("/order")
public class OrdersEndpoint {
	
	@Inject
	private OrderService service;
	
	@POST
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response placeOrder(OrderInfo orderDetails) {
		if (orderDetails == null)
			throw new WebExceptions("Invalid data to make an order").InvalidRequestException();

		Long orderId = service.placeOrder(orderDetails);
		return Response.ok("Order created with order id : " + orderId).build();
	}

}
