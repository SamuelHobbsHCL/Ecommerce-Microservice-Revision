package com.hcl.capstone.dto;

import com.hcl.capstone.model.User;
import com.hcl.capstone.model.Product;

public class UserReviewDto {
	private long id;
	private User dtoUser;
	private Product dtoProduct;
	private int dtoScore;
	private String dtoReview;

	public UserReviewDto(long id, User dtoUser, Product dtoProduct, int dtoScore, String dtoReview) {
		super();
		this.id = id;
		this.dtoUser = dtoUser;
		this.dtoProduct = dtoProduct;
		this.dtoScore = dtoScore;
		this.dtoReview = dtoReview;
	}
	
	public UserReviewDto( User dtoUser, Product dtoProduct, int dtoScore, String dtoReview) {
        super();
        this.dtoUser = dtoUser;
        this.dtoProduct = dtoProduct;
        this.dtoScore = dtoScore;
        this.dtoReview = dtoReview;
    }
	
	public UserReviewDto(int dtoScore, String dtoReview) {
		super();
		this.dtoScore = dtoScore;
		this.dtoReview = dtoReview;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getDtoUser() {
		return dtoUser;
	}

	public void setDtoUser(User dtoUser) {
		this.dtoUser = dtoUser;
	}

	public Product getDtoProduct() {
		return dtoProduct;
	}

	public void setDtoProduct(Product dtoProduct) {
		this.dtoProduct = dtoProduct;
	}

	public int getDtoScore() {
		return dtoScore;
	}

	public void setDtoScore(int dtoScore) {
		this.dtoScore = dtoScore;
	}

	public String getDtoReview() {
		return dtoReview;
	}

	public void setDtoReview(String dtoReview) {
		this.dtoReview = dtoReview;
	}

	

}
