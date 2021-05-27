package com.order.endpoints;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.order.entity.Product;
import com.order.exceptions.WebExceptions;
import com.order.service.ProductService;

@Path("/product")
public class ProductEndpoint {

	@Inject
	private ProductService service;

	@POST
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProduct(Product productDetails) {
		if (productDetails == null)
			throw new WebExceptions("Invalid data to create a product").InvalidRequestException();

		Long productId = service.createProduct(productDetails);
		return Response.ok("Product created with product id : " + productId).build();
	}

	@GET
	@Path("/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@PathParam("productId") Long id) {
		Product result = service.getProductById(id);
		if (result == null)
			throw new WebExceptions("No product found against given id").NotFoundException();
		return Response.ok(result).build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProducts() {
		List<Product> result = service.getProducts();
		if (result == null || result.size() == 0)
			throw new WebExceptions("No product available").NotFoundException();
		return Response.ok(result).build();
	}

}
