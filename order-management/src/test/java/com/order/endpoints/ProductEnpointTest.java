package com.order.endpoints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.order.entity.Product;
import com.order.service.ProductService;

public class ProductEnpointTest extends JerseyTest {

	@Mock
	private ProductService serviceMock;
	private Product productMock;

	@Before
	public void init() {
		productMock = new Product();
		productMock.setModelNo("ABC123");
		productMock.setPrice(2000.0);
		productMock.setProductName("XPER");
		productMock.setQuantity(2);
		productMock.setProductId(1L);
	}

	@After
	public void destroy() {
		productMock = null;
		serviceMock = null;
	}

	@Override
	protected Application configure() {
		MockitoAnnotations.initMocks(this);

		ResourceConfig config = new ResourceConfig(ProductEndpoint.class);
		config.register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(serviceMock).to(ProductService.class);
			}
		});
		return config;
	}

	@Test
	public void getProductTest() {
		Mockito.when(serviceMock.getProductById(Mockito.anyLong())).thenReturn(productMock);
		Response output = target("/product/1").request().get();
		assertNotNull(output.getEntity());
		assertEquals(200, output.getStatus());
	}

	@Test
	public void testCreate() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("modelNo", "XPR123");
		map.put("productName", "ABCD");
		map.put("productId", 1L);
		map.put("quantity", 2);
		map.put("price", 2000.0);

		Mockito.when(serviceMock.createProduct(Mockito.any(Product.class))).thenReturn(1L);

		Response output = target("/product/new").request().post(Entity.entity(map, MediaType.APPLICATION_JSON));
		assertEquals(201, output.getStatus());
	}

}
