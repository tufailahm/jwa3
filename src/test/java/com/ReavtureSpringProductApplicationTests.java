package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.training.model.Product;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class ReavtureSpringProductApplicationTests extends AbstractTest {

	@LocalServerPort
	private String port;

	int productId =0;
	private String baseURL = "http://localhost";

	URL restURL;

	@BeforeEach
	public void setUp() throws MalformedURLException {
		super.setUp();
		productId = 899;
		restURL = new URL(baseURL + ":" + port + "/product");
	}

	@Test
	@Order(5)
	@DisplayName("Testing product list all functionality")
	void testGetProducts() throws Exception {

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(restURL.toString()).accept(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("Product Content :  " + content);

		Product[] productList = super.mapFromJson(content, Product[].class);
		assertEquals(productList.length, 1);

	}

	@Test
	@Order(2)
	@DisplayName("Testing product update functionality")
	void testUpdateProduct() throws Exception {

		Product product = new Product();
		product.setProductId(productId);		//100
		product.setProductName("Monitor");
		product.setPrice(9800);
		product.setQuantityOnHand(100);

		// convert the object into json
		String inputJson = super.mapToJson(product);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.put(restURL.toString()).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String successMessage = "Product with product id :" + product.getProductId() + " updated successfully";
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, successMessage);

	}

	
	@Test
	@Order(3)
	@DisplayName("Testing product get a single record functionality")
	void testProduct() throws Exception {

			//To do hands on
	}
	
	
	
	//testing save
	@Test
	@Order(1)
	@DisplayName("Testing product save functionality")
	void testSaveProduct() throws Exception {

		Product product = new Product();
		product.setProductId(productId);		//100
		product.setProductName("HPLaptop");
		product.setPrice(9800);
		product.setQuantityOnHand(100);

		// convert the object into json
		String inputJson = super.mapToJson(product);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(restURL.toString()).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String successMessage = "Product saved successfully :" + product.getProductId();
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, successMessage);

	}
	
	
	
	//--Hands on -- test delete method
	
	@Test
	@Order(4)
	@DisplayName("Testing product delete functionality")
	void testDeleteProduct() throws Exception {
		//localhost:9090/product/2	- DELETE
		String deleteURL = restURL.toString() + "/"+productId;		//100
		
		//code to test delete method
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.delete(deleteURL)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(204, status);
		String successMessage = "Product deleted successfully";
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, successMessage);

	}
	
}
