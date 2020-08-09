package com.minenergia.test.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.minenergia.test.domain.Contact;
import com.minenergia.test.domain.ContactDTO;
import com.minenergia.test.domain.User;
import com.minenergia.test.repository.ContactRepository;
import com.minenergia.test.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private ContactRepository repoContacts;
	
    @GetMapping("/account")
    public User getAccount() {
    	
    	Long id=(long) 1;
    	Optional<User> user = repo.findById(id);
          	
        return user.get();
    }
    
    @PostMapping("/contacts/news")
    public void createContacts(@Validated @RequestBody ContactDTO dto) {
    	
    	Contact contact = new Contact();
    	
    	contact.setFirstName(dto.getFirstName().toLowerCase());
    	contact.setLastName(dto.getLastName().toLowerCase());
    	contact.setAddress(dto.getAddress().toLowerCase());
    	contact.setPhones(dto.getPhones());
    	contact.setPhoto(dto.getPhoto());
    	contact.setUser(dto.getUser());
    	
    	repoContacts.save(contact);    
    	
    }

    
    @PostMapping("/contacts")
    public void updateContacts(@RequestBody ContactDTO dto) throws Exception {
    	
  
        if(dto.getId()==null)
        	throw new Exception("no found id");	
        
    	Contact contact = repoContacts.findById(dto.getId()).get();;
 
    	contact.setFirstName(dto.getFirstName().toLowerCase());
    	contact.setLastName(dto.getLastName().toLowerCase());
    	contact.setAddress(dto.getAddress().toLowerCase());
    	contact.setPhones(dto.getPhones());
    	contact.setPhoto(dto.getPhoto());
       	
    	repoContacts.save(contact);    
    	
    }
    
    @GetMapping("/account/contacts")
    public Collection<Contact> getAllContactsByUserId(Long id) {
        	
        return repoContacts.findByUserId(id);
        		
    } 
    
    @GetMapping("/contacts/names")
    public List<Contact> getContactsByName(String name) {
         	
        return repoContacts.findByFirstNameContaining(name.toLowerCase());
       		
    } 
    
    @GetMapping("/contacts")
    public Contact getContactsById(Long id) {
         	
        return repoContacts.findById(id).get();
        		
    } 
}
