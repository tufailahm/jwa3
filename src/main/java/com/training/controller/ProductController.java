package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.model.Product;
import com.training.repository.ProductRepository;

@RequestMapping("product")
@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {

		List<Product> products = (List<Product>) productRepository.findAll();
		if (products.size() == 0)
			return new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
}
