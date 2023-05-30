package com.example.demo.repostiory;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Serializable>{

	
}
