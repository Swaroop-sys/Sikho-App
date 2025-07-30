package com.example.ecommerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.AuthModel;

@Repository
public interface AuthRepository extends JpaRepository<AuthModel, Long>{
	Optional<AuthModel>findByEmail(String email);
    boolean existsByEmail(String email);


}
