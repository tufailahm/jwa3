package com.training.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.model.Product;
import com.training.service.ProductService;

//CRUD - operation completed successfully
@RestController
@RequestMapping("product")
//@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	ProductService productService;

	// localhost:9090/product/
	@GetMapping()
	public List<Product> getProducts(@RequestParam (required = false)String productName) {
		
		List<Product> products = new ArrayList<Product>();
		
		if(productName == null)
		{
			//the client wants all the products
			products = productService.getProducts();
		}
		else
		{
			//the client wants filtered products
			products = productService.getProductByName(productName);
		}
		
		return products;
	}

	// localhost:9090/product/iPhone
	@GetMapping("/searchProductName/{productName}")
	public List<Product> getProductByName(@PathVariable("productName") String productName) {
		return productService.getProductByName(productName);
	}

	// get a single record
	// localhost:9090/product/798
	@GetMapping("{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") int productId) {

		ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(HttpStatus.OK);
		Product product = new Product();
		// two scenario
		if (productService.isProductExists(productId)) {
			product = productService.getProduct(productId);
			responseEntity = new ResponseEntity<Product>(product, HttpStatus.OK);
			// product exists
		} else {
			responseEntity = new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT);
		}

		return responseEntity;
	}

	// save functionality
	@PostMapping()
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
		// two scenario
		if (productService.isProductExists(product.getProductId())) {
			responseEntity = new ResponseEntity<String>(
					"Product with product id :" + product.getProductId() + " already exists", HttpStatus.CONFLICT);
			// product exists
		} else {
			productService.addProduct(product);
			responseEntity = new ResponseEntity<String>("Product saved successfully :" + product.getProductId(),
					HttpStatus.CREATED);
		}
		return responseEntity;
	}

	// update functionality
	@PutMapping()
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
		// two scenario
		if (productService.isProductExists(product.getProductId())) {
			productService.updateProduct(product);

			responseEntity = new ResponseEntity<String>(
					"Product with product id :" + product.getProductId() + " updated successfully", HttpStatus.OK);
			// product exists
		} else {
			responseEntity = new ResponseEntity<String>("Product not updated successfully :" + product.getProductId(),
					HttpStatus.NOT_MODIFIED);
		}
		return responseEntity;
	}

	// delete
	// http://localhost:9090/product/2009
	@DeleteMapping("{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") int productId) {

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
		// two scenario
		if (productService.isProductExists(productId)) {
			productService.deleteProduct(productId);
			responseEntity = new ResponseEntity<String>("Product deleted successfully", HttpStatus.NO_CONTENT);
			// product exists
		} else {
			responseEntity = new ResponseEntity<String>("Product not deleted successfully", HttpStatus.OK);
		}

		return responseEntity;
	}

}
