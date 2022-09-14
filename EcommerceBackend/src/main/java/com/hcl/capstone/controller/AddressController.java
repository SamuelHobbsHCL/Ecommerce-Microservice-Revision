package com.hcl.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.capstone.model.Address;
import com.hcl.capstone.service.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/user/getUserAddress")
    public Address getUserAddress(Authentication authentication) {
    	return addressService.getUserAddress(authentication);
    }

}
