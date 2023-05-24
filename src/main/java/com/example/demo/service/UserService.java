package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.bindings.LoginDto;
import com.example.demo.bindings.LoginResponse;
import com.example.demo.bindings.RegistrationDto;
import com.example.demo.exception.ResourceNotFoundException;

public interface UserService {

	public ResponseEntity<?> register(RegistrationDto dto);
	
	public RegistrationDto findUser(Integer userId) throws ResourceNotFoundException;
	
	public LoginResponse login(LoginDto login);
	
	public RegistrationDto updateUser(RegistrationDto dto,Integer userId) throws ResourceNotFoundException;
	
	ResponseEntity<?> confirmEmail(String confirmationToken);
}
