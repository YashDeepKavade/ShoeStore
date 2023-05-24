package com.example.demo.bindings;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class RegistrationDto {

	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer userId;

	@NotBlank
	@NotEmpty(message = "Name shouldn't be empty")
	private String name;

	@Email(message = "Email Not Valid")
	@NotEmpty(message = "Email shouldn't be empty")
	private String email;

	@NotBlank(message = "Password shouldn't be empty")
	@JsonProperty(access = Access.WRITE_ONLY)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Use 8 or more characters with a mix of letters, numbers & symbols", flags = Flag.UNICODE_CASE)
	private String password;
}
