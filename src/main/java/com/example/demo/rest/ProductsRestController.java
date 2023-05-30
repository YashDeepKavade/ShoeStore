package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bindings.ProductDto;
import com.example.demo.service.ProductService;

@RestController
public class ProductsRestController {

	@Autowired
	private ProductService service;
	
	@PostMapping("/addproduct")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product){
		System.out.println(product);
		ProductDto addedProduct = service.addProduct(product);
		return new ResponseEntity<ProductDto>(addedProduct,HttpStatus.CREATED);
	}
	
	@GetMapping("/getproduct/{pId}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable Integer pId){
		ProductDto product = service.getProduct(pId);
		return new ResponseEntity<ProductDto>(product,HttpStatus.OK);
	}
	
	@GetMapping("/getallproducts")
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		List<ProductDto> allProducts = service.getAllProducts();
		return new ResponseEntity<List<ProductDto>>(allProducts,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteproduct/{pId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer pId){
		String deleteProduct = service.deleteProduct(pId);
		return new ResponseEntity<String>(deleteProduct,HttpStatus.OK);
	}
	
}
