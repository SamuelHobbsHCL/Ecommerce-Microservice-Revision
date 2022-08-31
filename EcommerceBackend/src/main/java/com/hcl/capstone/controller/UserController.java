package com.hcl.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.capstone.model.User;
import com.hcl.capstone.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
	
	@Autowired
	private UserService userService;
	
    @GetMapping("/api/test")
    public String test() {
    	return "Test Endpoint works";
    }
    
    @GetMapping("/user/getCurrentUser")
    public User getCurrentLoggedInUser(Authentication authentication) {
    	User user = userService.getCurrentLoggedInUser(authentication);
    	return user;
    }
    
    @PutMapping("/user/update/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long id){
		
		User result = userService.updateUser(user, id);
		
		if(result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
}
