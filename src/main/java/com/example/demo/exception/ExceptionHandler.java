package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleManfe(MethodArgumentNotValidException ex){
		
		Map<String,String> mapError=new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(err->{
			mapError.put(err.getField(),err.getDefaultMessage());
		});
		
		return mapError;
		
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
	public Map<String,String> handleRnfe(ResourceNotFoundException ex){
		Map<String,String> mapError=new HashMap<>();
		mapError.put("Error Message",ex.getMessage());
		return mapError;
	}
	
}
