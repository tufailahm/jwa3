package com;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ReavtureSpringAppApplicationTests {

	@LocalServerPort
	private String port;

	private String baseURL = "http://localhost";

	URL restURL;

	@Autowired
	RestTemplate restTemplate;
	
	@BeforeEach
	public void setUp() throws MalformedURLException {
		// http://localhost:9090/home
		restURL = new URL(baseURL + ":" + port + "/home");
	}

	@Test
	void testHomeAPI() {
				ResponseEntity<String> response =  restTemplate.getForEntity(restURL.toString(), 
						String.class);
				assertEquals("---------Welcome to Revature APP --------", response.getBody());
			
	}

}
