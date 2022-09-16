package com.hcl.capstone.controller;

import org.apache.commons.lang3.StringUtils;
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

import com.hcl.capstone.dto.AddressDto;
import com.hcl.capstone.dto.PasswordDto;
import com.hcl.capstone.dto.UpdateImageDto;
import com.hcl.capstone.model.Address;
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
    
	
	@GetMapping("/user/getUserAddress")
    public Address getUserAddress(Authentication authentication) {
    	return userService.getUserAddress(authentication);
    }
	
	@PutMapping("/user/update-address")
	public Address updateAddress(Authentication authentication, @RequestBody AddressDto addressDTO) {
		return userService.updateAddress(authentication, addressDTO);
	}
    
    //update user password
    @PutMapping("/user/update-password")
    public ResponseEntity<String> updatePassword(Authentication authentication, @RequestBody PasswordDto passwordDTO){
    	boolean result = userService.updateUserPassword(passwordDTO, authentication);
    	if(result == false) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			String res = "Your password has been updated successfully";
			return new ResponseEntity<>(res, HttpStatus.OK);
		}

    }
    
    @PutMapping("/user/update-profile-image")
    public ResponseEntity<String> updateImage(Authentication authentication, @RequestBody UpdateImageDto updateImageDTO) {
    	if(StringUtils.isNotEmpty(updateImageDTO.getImageUrl())) {

    		userService.updateImage(authentication, updateImageDTO.getImageUrl());
    		
    		return new ResponseEntity<>("Image saved successfully", HttpStatus.OK);
    		
    	} else {
    		return new ResponseEntity<>("Please provide image url", HttpStatus.BAD_REQUEST);
    	}
		
    }
    
	@DeleteMapping("/user/delete-user")
	public ResponseEntity<String> deleteCurrentUser(Authentication authentication) {
		
		boolean result = userService.deleteUser(authentication);
		if(result == true) {
			return new ResponseEntity<>("Successfully deleted account!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Fail to delete. Please try again!", HttpStatus.BAD_REQUEST);
		}
	}
	
} 
