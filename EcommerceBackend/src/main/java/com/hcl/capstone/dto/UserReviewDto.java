package com.hcl.capstone.dto;

import com.hcl.capstone.model.User;
import com.hcl.capstone.model.Product;

public class UserReviewDto {
	private long id;
	private User user;
	private Product product;
	private int score;
	private String review;
	
	UserReviewDto(){}
	
	public UserReviewDto( int score ,String review, Product product) {
        super();
        System.out.println("Building a dto");
        System.out.println("Values are score: " + score + " user: " + user + " Product: " + product + " review: " + review);
        this.product = product;
        this.score = score;
        this.review = review;
    }
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getDtoUser() {
		return user;
	}

	public void setUser(User dtoUser) {
		this.user = dtoUser;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product dtoProduct) {
		this.product = dtoProduct;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int dtoScore) {
		this.score = dtoScore;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String dtoReview) {
		this.review = dtoReview;
	}

	

}
