package com.order.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.order.dao.OrderMangementDAO;
import com.order.dao.OrderManagementDAOImpl;
import com.order.service.OrderService;
import com.order.service.ProductService;


public class AppBinder extends AbstractBinder {
    @Override
    protected void configure() {
    	bind(new OrderManagementDAOImpl()).to(OrderMangementDAO.class);
    	bind(OrderService.class).to(OrderService.class);
    	bind(ProductService.class).to(ProductService.class);
    }
}

