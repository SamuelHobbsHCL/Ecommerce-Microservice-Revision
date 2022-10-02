package com.hcl.capstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.capstone.dto.UserReviewDto;
import com.hcl.capstone.model.Product;
import com.hcl.capstone.model.UserReview;
import com.hcl.capstone.repository.ProductRepository;
import com.hcl.capstone.service.UserReviewService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class UserReviewController {
	@Autowired
	private UserReviewService userReviewService;
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("api/review")
	public UserReview createReview(@RequestBody UserReviewDto userReviewDto) {
		return userReviewService.submitReview(userReviewDto);
	}
	
	@GetMapping("api/review/{id}")
	public List<UserReview> getProductReviews(@PathVariable long id){
	    Product product = productRepository.findById(id);
	    System.out.println(product.getProductName());
		return userReviewService.getProductReviews(product);
	}
	
	
}
