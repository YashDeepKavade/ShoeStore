package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repostiory.CartRepository;
import com.example.demo.repostiory.ProductRepository;
import com.example.demo.repostiory.UserRepository;

public class CartServiceImpl implements CartService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductRepository proRepo;
	
	@Autowired
	private CartRepository cartRepo;

	@Override
	public String addToCart(Integer pId, Integer userId) {
		
		
		
		
		return null;
	}

}
