package com.hcl.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.capstone.dto.UserReviewDto;
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
}
