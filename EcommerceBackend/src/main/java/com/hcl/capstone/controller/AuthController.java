package com.hcl.capstone.controller;

import java.io.IOException;

import javax.mail.MessagingException;
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
		System.out.print(loginRequest.getEmail() + " " + loginRequest.getPassword());
		return tokenProvider.createToken(loginRequest);
	}
	
	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public User addUser(@RequestBody User user) throws MessagingException, IOException {
		
		return userService.registerUser(user);
	}
}
