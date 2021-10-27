package com.training.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jwaproducts")
@ToString
public class Product {

	@Id
	private int productId;
	private String productName;
	private int quantityOnHand;
	private int price;
}
