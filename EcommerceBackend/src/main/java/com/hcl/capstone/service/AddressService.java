package com.hcl.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.hcl.capstone.dto.AddressDTO;
import com.hcl.capstone.model.Address;
import com.hcl.capstone.model.User;
import com.hcl.capstone.repository.AddressRepository;
import com.hcl.capstone.repository.UserRepository;

@Service
public class AddressService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address getUserAddress(Authentication authentication) {
		
		User user = userService.getCurrentLoggedInUser(authentication);
	
		return addressRepository.findById(user.getAddressId());
	}
	
	public Address updateAddress(Authentication authentication, AddressDTO addressDTO) {
		
		Address newAddress = new Address();
		
		newAddress.setStreet(addressDTO.getStreet());
		newAddress.setUnit(addressDTO.getUnit());
		newAddress.setCity(addressDTO.getCity());
		newAddress.setState(addressDTO.getState());
		newAddress.setZipcode(addressDTO.getZipcode());
		newAddress.setCountry(addressDTO.getCountry());
		
		Address savedAddress = addressRepository.save(newAddress);
		
		User currentUser = userService.getCurrentLoggedInUser(authentication);
		
		currentUser.setAddressId(savedAddress.getAddressId());
		
		userRepository.save(currentUser);
		
		return savedAddress;
	}

}
