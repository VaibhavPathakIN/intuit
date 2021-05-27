# Order Management App

##Case Study
```
You need to design/develop some Order Management related RESTful APIs that orchestrates among multiple enterprise systems, and these APIs will in turn be used by other customer-facing products.
 
Assume the below upstream & downstream enterprise systems that these APIs would interact with:
 
Upstream System that exists that will be using the REST APIs:
Customer-facing UI website where customers can buy some products. This UI website will call your REST APIs to accomplish the following:
1.	Get a price quote for a given product
2.	Submit an order to purchase the product
3.	Handle resilience scenarios for API failures.
4.	Demonstrate an example for scalability for service.
```

###Project Description:

	This service has been created by using Jersey framework.
	To handle all basic functionality, below endpoints are present.
		1. ProductEndpoit:
			1. product/new : To create a new product in the system
			2. product/{productId} : To get the existing product details
			3. product/all : To get all products present in system
		2. OrdersEnpoint:
			1.order/new : To place an order

###Project Execution Steps:

    Pre-requisites:
    1. Java version 1.7 must be installed.
    2. Install maven module (https://maven.apache.org/install.html). Apache Maven 3.6.3 is required.

    Steps:
    1. To run this application, do the following:
        1.a. Go to the project root directory.
        1.b. Run the following commands in the terminal/command line to build and run the service:
            - mvn jetty:run
    2. Go to http://localhost:8080/ in your browser to view it.
    
###Project Test Steps:

    Pre-requisites:
    1. Application should be in running state on embeded server.

    Commands:
    1. Create a product : product/new
	    ```
	    curl -X POST -H "Content-Type: application/json" -d "{\"productName\": \"Product1\", \"modelNo\": \"ABC123\", \"price\": \"1040\", \"quantity\": \"2\"}" http://localhost:8080/api/product/new
	    ```    
    2. Get All products : product/all
	    ```
	    curl -X GET http://localhost:8080/api/product/all
	    ```
	 3. Get a product against an id : product/{productId}
	    ```
	    curl -X GET http://localhost:8080/api/product/1
	    ```
    4. Place an order : order/new
	    ```
	    curl -X POST -H "Content-Type: application/json" -d "{\"customerId\": \"abc_1@gmail.com\", \"deliveryAddress\": \"Delhi\", \"orderSummary\": [{\"productId\":\"1\", \"quantity\":\"2\"}]}" http://localhost:8080/api/order/new
	    ```