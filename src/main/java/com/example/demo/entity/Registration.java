package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;
	
	private Boolean isEnabled;

}
