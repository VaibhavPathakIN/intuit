package com.order.config;

import org.glassfish.jersey.server.ResourceConfig;

public class OrderManagementApp extends ResourceConfig {
	public OrderManagementApp() {
		register(new AppBinder());
		packages(true, "com.order.config");
	}
}
