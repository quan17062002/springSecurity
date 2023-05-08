package com.luucungquan.securityfirst.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luucungquan.securityfirst.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	 boolean existsByUsername(String username);
   Optional<User> findByUsername(String username);

}
