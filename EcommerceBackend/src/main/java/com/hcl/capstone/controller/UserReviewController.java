package com.hcl.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.capstone.dto.UserReviewDto;
import com.hcl.capstone.model.UserReview;
import com.hcl.capstone.service.UserReviewService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class UserReviewController {
	@Autowired
	private UserReviewService userReviewService;
	
	@PostMapping("/review")
	public UserReview createReview(@RequestBody UserReviewDto userReviewDto) {
		return userReviewService.submitReview(userReviewDto);
	}
	
	
}
