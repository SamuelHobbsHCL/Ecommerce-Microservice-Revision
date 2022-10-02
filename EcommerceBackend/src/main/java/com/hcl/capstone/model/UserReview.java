package com.hcl.capstone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcl.capstone.dto.UserReviewDto;

@Entity
@Table(name = "USER_REVIEW")
public class UserReview {
    public UserReview(){};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REVIEW_ID")
	private long reviewId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="USER_ID", referencedColumnName = "USER_ID")
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
	private Product product;
	
	@Column(name="SCORE")
	private int score;
	
	@Column(name="REVIEW")
	private String review;
	
	
	
	public UserReview(UserReviewDto userReviewDto) {
		super();
		this.user = userReviewDto.getDtoUser();
		this.product = userReviewDto.getDtoProduct();
		this.score = userReviewDto.getDtoScore();
		this.review = userReviewDto.getDtoReview();
	}
}
