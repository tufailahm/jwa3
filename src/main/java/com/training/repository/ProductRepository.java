package com.training.repository;

import org.springframework.data.repository.CrudRepository;

import com.training.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
