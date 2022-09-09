package com.hcl.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.hcl.capstone.model.Address;
import com.hcl.capstone.model.User;
import com.hcl.capstone.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address getUserAddress(Authentication authentication) {
		
		User user = userService.getCurrentLoggedInUser(authentication);
	
		return addressRepository.findById(user.getAddressId());
	}

}
