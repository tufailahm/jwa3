package com;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import com.training.model.Product;
import com.training.repository.ProductDAO;

@SpringBootTest
public class ProductRepositoryTest {

	@Autowired
	private ProductDAO productRepository;
	
	@Test
	@DisplayName("Testing Repository save")
	public void testSave() {
		Product product = new Product(3, "Mouse", 122, 90);
		List<Product> products = (List<Product>) productRepository.findAll();
		int originalSize = products.size();		//0
		productRepository.save(product);
		assertEquals((originalSize+1),2);
	}
}
