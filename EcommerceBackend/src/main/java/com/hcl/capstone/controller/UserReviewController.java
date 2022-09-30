package com.hcl.capstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	@PostMapping("api/review")
	public UserReview createReview(@RequestBody UserReviewDto userReviewDto) {
		return userReviewService.submitReview(userReviewDto);
	}
	
	@GetMapping("api/review/{id}")
	public List<UserReview> getProductReviews(long id){
		return userReviewService.getProductReviews(id);
	}
}
