package com.example.demo.service;

import java.util.List;

import com.example.demo.bindings.ProductDto;

public interface ProductService {

	public ProductDto addProduct(ProductDto product);
	
	public ProductDto getProduct(Integer pId);
	
	public List<ProductDto> getAllProducts();
	
	public String deleteProduct(Integer pId);
}
