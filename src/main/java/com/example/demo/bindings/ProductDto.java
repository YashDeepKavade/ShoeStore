package com.example.demo.bindings;

import lombok.Data;

@Data
public class ProductDto {

	private Integer productId;

	private String productName;

	private Double price;

	private Integer quantity;
}
