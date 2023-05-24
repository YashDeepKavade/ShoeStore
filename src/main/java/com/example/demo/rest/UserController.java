package com.example.demo.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bindings.LoginDto;
import com.example.demo.bindings.LoginResponse;
import com.example.demo.bindings.RegistrationDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.UserServiceImpl;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserServiceImpl service;

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegistrationDto dto){
		
		return service.register(dto);
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<RegistrationDto> findUser(@PathVariable Integer userId) throws ResourceNotFoundException{
		
		RegistrationDto findUser = service.findUser(userId);
		return new ResponseEntity<RegistrationDto>(findUser,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDto loginDto){
		LoginResponse loginStatus = service.login(loginDto);
		return new ResponseEntity<LoginResponse>(loginStatus,HttpStatus.OK);
	}
	
	@PostMapping("/updateuser/{userId}")
	public ResponseEntity<RegistrationDto> updateUser(@Valid @RequestBody RegistrationDto Dto,@PathVariable Integer userId) throws ResourceNotFoundException{
		 RegistrationDto updatedUser = service.updateUser(Dto, userId);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
	}
	
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return service.confirmEmail(confirmationToken);
    }

	
	
}
