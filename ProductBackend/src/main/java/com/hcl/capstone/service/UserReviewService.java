package com.hcl.capstone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.capstone.dto.UserReviewDto;
import com.hcl.capstone.model.Product;
import com.hcl.capstone.model.UserReview;
import com.hcl.capstone.repository.UserReviewRepository;

@Service
public class UserReviewService {

	@Autowired
	private UserReviewRepository userReviewRepository;
	
	public UserReview submitReview(UserReview userReview) {
		return userReviewRepository.save(userReview);
	}
	
	public List<UserReview> getProductReviews(Product product){
	    
	    return userReviewRepository.findByProduct(product);
	}
	
	public double getProductReviewAverage(Product product){
	    double average = 0;
	    int count = 0;
	    List<UserReview> userReviews = userReviewRepository.findByProduct(product);
	    count = userReviews.size();
	    for(UserReview review : userReviews) {
	        average = average + review.getScore();
	    }
        return average/count;
    }
	
	public UserReview updateReview(UserReviewDto userReviewDto) {
		Optional<UserReview> reviewRepo = Optional.ofNullable(userReviewRepository.findById(userReviewDto.getId()));
		
		if(!reviewRepo.isPresent()) {
			return null;
		}
		
		UserReview update = new UserReview(userReviewDto.getDtoUser(), userReviewDto.getProduct(), userReviewDto.getScore(), userReviewDto.getReview());
		
		userReviewRepository.save(update);
		
		return userReviewRepository.findById(userReviewDto.getId());
	}
}
