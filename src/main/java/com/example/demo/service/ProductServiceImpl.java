package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bindings.ProductDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repostiory.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public ProductDto addProduct(ProductDto product) {
		ProductEntity entity=new ProductEntity();
		BeanUtils.copyProperties(product, entity);
		System.out.println(entity);
		ProductEntity savedProduct = productRepository.save(entity);
		BeanUtils.copyProperties(savedProduct, product);
		return product;
	}

	@Override
	public ProductDto getProduct(Integer pId) {

		ProductEntity productEntity = productRepository.findById(pId).get();
		ProductDto dto=new ProductDto();
		BeanUtils.copyProperties(productEntity, dto);
		return dto;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<ProductEntity> entity = productRepository.findAll();
		List<ProductDto> dtos=new ArrayList<>();
		for(ProductEntity product:entity) {
			ProductDto dto=new ProductDto();
			BeanUtils.copyProperties(product, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public String deleteProduct(Integer pId) {
		productRepository.deleteById(pId);
		return "Product Deleted Successfully !";
	}

}
