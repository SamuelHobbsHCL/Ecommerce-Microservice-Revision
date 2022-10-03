package com.hcl.capstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.capstone.dto.UserReviewDto;
import com.hcl.capstone.model.Product;
import com.hcl.capstone.model.UserReview;
import com.hcl.capstone.repository.ProductRepository;
import com.hcl.capstone.service.UserReviewService;
import com.hcl.capstone.service.UserService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserReviewController {
	@Autowired
	private UserReviewService userReviewService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/review")
	@ResponseStatus(HttpStatus.CREATED)
	public UserReview createReview(Authentication authentication, @RequestBody UserReviewDto userReviewDto) {
	    System.out.println("I am in the review creation??");
	    System.out.println(userReviewDto.getReview());
	    System.out.println(userReviewDto.getScore());
	    System.out.println(userReviewDto.getProduct());
	    userReviewDto.setUser(userService.getCurrentLoggedInUser(authentication));
	    System.out.println(userReviewDto.getDtoUser());
	    UserReview newReview = new UserReview(userReviewDto);
		return userReviewService.submitReview(newReview);
	}
	
	@GetMapping("/api/review/{id}")
	public List<UserReview> getProductReviews(@PathVariable(value = "id") long id){
	    Product product = productRepository.findById(id);
		return userReviewService.getProductReviews(product);
	}
	
	
}
