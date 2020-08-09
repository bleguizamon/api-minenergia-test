package com.minenergia.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minenergia.test.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	 Optional<User> findById(Long id);
	 
}
