package com.hcl.capstone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
	
    @GetMapping("/api/test")
    public String test() {
    	return "Test Endpoint works";
    }
	
}
