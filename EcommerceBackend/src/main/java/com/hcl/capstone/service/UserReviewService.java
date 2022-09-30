package com.hcl.capstone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.capstone.dto.ProductDto;
import com.hcl.capstone.dto.UserReviewDto;
import com.hcl.capstone.model.Product;
import com.hcl.capstone.model.UserReview;
import com.hcl.capstone.repository.UserReviewRepository;

@Service
public class UserReviewService {

	@Autowired
	private UserReviewRepository userReviewRepository;
	
	public UserReview submitReview(UserReviewDto userReviewDto) {
		UserReview userReview = new UserReview(userReviewDto);
		return userReviewRepository.save(userReview);
	}
	
	public List<UserReview> getProductReviews(long id){
		return userReviewRepository.findByProductId(id);
	}
	
	public UserReview updateReview(UserReviewDto userReviewDto) {
		Optional<UserReview> reviewRepo = Optional.ofNullable(userReviewRepository.findById(userReviewDto.getId()));
		
		if(!reviewRepo.isPresent()) {
			return null;
		}
		
		UserReview update = new UserReview(userReviewDto);
		
		userReviewRepository.save(update);
		
		return userReviewRepository.findById(userReviewDto.getId());
	}
}
