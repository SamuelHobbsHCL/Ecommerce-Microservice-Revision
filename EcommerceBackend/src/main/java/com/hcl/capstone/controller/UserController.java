package com.hcl.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.capstone.dto.PasswordDTO;
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
    	return userService.getCurrentLoggedInUser(authentication);
    }
    
    @PutMapping("/user/update/{id}")
	public ResponseEntity<User> updateUser(Authentication authentication, @RequestBody User user, @PathVariable long id){
    	User currentUser = userService.getCurrentLoggedInUser(authentication);
    	User result;
    	
    	if(currentUser.getUserId() == id) {
    		result = userService.updateUser(user, id);
    	} else {
    		result = null;
    	}
		
		if(result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
    
    //update user password
    @PutMapping("/user/update-password")
    public ResponseEntity<String> updatePassword(Authentication authentication, @RequestBody PasswordDTO passwordDTO){
    	boolean result = userService.updateUserPassword(passwordDTO, authentication);
    	if(result == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			String res = "Your password has been updated successfully";
			return new ResponseEntity<>(res, HttpStatus.OK);
		}

    }
    
	@DeleteMapping("/user/delete-user")
	public void deleteCurrentUser(Authentication authentication) {
		User currentUser = userService.getCurrentLoggedInUser(authentication);
		userService.deleteUserById(currentUser.getUserId());
	}
	
}
