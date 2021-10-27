package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class ReavtureSpringProductApplicationTests extends AbstractTest {

	@LocalServerPort
	private String port;

	private String baseURL = "http://localhost";

	URL restURL;

	@BeforeEach
	public void setUp() throws MalformedURLException {
		super.setUp();
		restURL = new URL(baseURL + ":" + port + "/product");
	}

	@Test
	void testGetProducts() throws Exception {

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(restURL.toString()).accept(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("Product Content :  " + content);

		Product[] productList = super.mapFromJson(content, Product[].class);
		assertEquals(productList.length, 2);

	}

	@Test
	void testUpdateProduct() throws Exception {

		Product product = new Product();
		product.setProductId(43);
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

	//--Hands on -- test delete method
	
	@Test
	void testDeleteProduct() throws Exception {

		String deleteURL = restURL.toString() + "/2";
		
		//code to test delete method

	}
	
}
