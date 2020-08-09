package com.minenergia.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minenergia.test.domain.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
	
	List<Contact> findByUserId(Long id);
	
	List<Contact> findByFirstNameContaining(String name);

}
