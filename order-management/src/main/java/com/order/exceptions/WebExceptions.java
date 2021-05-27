package com.order.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class WebExceptions extends WebApplicationException {
	
	private static final long serialVersionUID = 1L;
	private String message;
		
    public WebExceptions(String message) {    	
    	this.message = message;
    }
    
    public WebApplicationException NotFoundException() {
    	return new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                .entity(message).type(MediaType.TEXT_PLAIN).build());
    }
    
    public WebApplicationException InvalidRequestException() {
    	return new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                .entity(message).type(MediaType.TEXT_PLAIN).build());
    }
    
    public WebApplicationException InternalException() {
    	return new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(message).type(MediaType.TEXT_PLAIN).build());
    }
    
}
