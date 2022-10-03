package com.hcl.capstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.capstone.dto.UserReviewDto;
import com.hcl.capstone.model.Product;
import com.hcl.capstone.model.User;
import com.hcl.capstone.model.UserReview;
import com.hcl.capstone.repository.ProductRepository;
import com.hcl.capstone.service.UserReviewService;
import com.hcl.capstone.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class UserReviewController {
	@Autowired
	private UserReviewService userReviewService;
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("api/review")
	public UserReview createReview(@RequestBody UserReviewDto userReviewDto) {
	    System.out.println(userReviewDto.getDtoReview());
	    System.out.println(userReviewDto.getDtoScore());
	    System.out.println(userReviewDto.getDtoProduct());
	    System.out.println(userReviewDto.getDtoUser());
	    UserReview newReview = new UserReview(userReviewDto);
		return userReviewService.submitReview(newReview);
	}
	
	@GetMapping("api/review/{id}")
	public List<UserReview> getProductReviews(@PathVariable(value = "id") long id){
	    Product product = productRepository.findById(id);
		return userReviewService.getProductReviews(product);
	}
	
	
}
