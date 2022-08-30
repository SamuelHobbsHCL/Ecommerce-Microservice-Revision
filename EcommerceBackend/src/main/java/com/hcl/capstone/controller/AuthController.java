package com.hcl.capstone.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.capstone.model.User;
import com.hcl.capstone.model.payload.AuthResponse;
import com.hcl.capstone.model.payload.LoginRequest;
import com.hcl.capstone.security.jwt.TokenProvider;
import com.hcl.capstone.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private TokenProvider tokenProvider;
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public AuthResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

		return tokenProvider.createToken(loginRequest);
	}
	
	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public User addUser(@RequestBody User user) throws AddressException, MessagingException, IOException {
		return userService.registerUser(user);
	}

//	    @PostMapping("/signup")
//	    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//	        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
//	            throw new BadRequestException("Email address already in use.");
//	        }
//
//	        // Creating user's account
//	        User user = new User();
//	        user.setName(signUpRequest.getName());
//	        user.setEmail(signUpRequest.getEmail());
//	        user.setPassword(signUpRequest.getPassword());
//	        user.setProvider(AuthProvider.local);
//
//	        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//	        User result = userRepository.save(user);
//
//	        URI location = ServletUriComponentsBuilder
//	                .fromCurrentContextPath().path("/user/me")
//	                .buildAndExpand(result.getId()).toUri();
//
//	        return ResponseEntity.created(location)
//	                .body(new ApiResponse(true, "User registered successfully@"));
//	    }

}
