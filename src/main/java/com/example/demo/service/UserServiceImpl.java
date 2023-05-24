package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.example.demo.bindings.LoginDto;
import com.example.demo.bindings.LoginResponse;
import com.example.demo.bindings.RegistrationDto;
import com.example.demo.entity.ConfirmationToken;
import com.example.demo.entity.Registration;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repostiory.ConfirmationTokenRepository;
import com.example.demo.repostiory.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private EmailService emailService;
	

	@Override
	public ResponseEntity<?> register(RegistrationDto dto) {
		Registration reg=new Registration();
		reg.setEmail(dto.getEmail());
		reg.setName(dto.getName());
		reg.setPassword(dto.getPassword());
		
		repo.save(reg);
		
		ConfirmationToken confirmationToken = new ConfirmationToken(reg);
		
		confirmationTokenRepository.save(confirmationToken);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(dto.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
		
	}

	@Override
	public RegistrationDto findUser(Integer userId) throws ResourceNotFoundException {
		System.out.println(userId);
		RegistrationDto dto=new RegistrationDto();
		Registration findById = repo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with id "+userId));
		
		BeanUtils.copyProperties(findById,dto);
		return dto;
	}

	@Override
	public LoginResponse login(LoginDto login) {
		Registration email = repo.findByEmail(login.getEmail());
		if(email!=null) {
			String proPass = login.getPassword();
			String dbPass = email.getPassword();
			Boolean isPwdRight= proPass.equals(dbPass);
			if(isPwdRight) {
				Optional<Registration> user = repo.findByEmailAndPassword(login.getEmail(),dbPass);
				if(user.isPresent()) {
					return new LoginResponse("Login Successful",true);
				}else {
					return new LoginResponse("Login Failed",false);
				}
			}else {
				return new LoginResponse("Password not matched",false);
			}
		}else {
			return new LoginResponse("Email not matched",false);
		}

	}

	@Override
	public RegistrationDto updateUser(RegistrationDto dto, Integer userId) throws ResourceNotFoundException {
		 System.out.println(dto);
		 Registration user = repo.findById(userId).orElseThrow(()->new ResourceNotFoundException("Resource not found with user id :"+userId));
		 System.out.println(user);
		 BeanUtils.copyProperties(dto,user);
		 user.setUserId(userId);
		 System.out.println(user);
		 Registration updatedUser = repo.save(user);
		 BeanUtils.copyProperties(updatedUser,dto);
		 
		 
		return dto;
	}

	@Override
	public ResponseEntity<?> confirmEmail(String confirmationToken) {

		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            Registration user = repo.findByEmail(token.getUser().getEmail());
            user.setIsEnabled(true);
            repo.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
   		
		
	}

	


	
}
