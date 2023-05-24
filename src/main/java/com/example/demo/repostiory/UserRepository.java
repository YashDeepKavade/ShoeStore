package com.example.demo.repostiory;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Registration;


@Repository
public interface UserRepository extends JpaRepository<Registration,Serializable>{

	public Registration findByEmail(String email);
	
	Optional<Registration> findByEmailAndPassword(String email, String password);
	
	public Boolean existsByEmail(String email);
}
