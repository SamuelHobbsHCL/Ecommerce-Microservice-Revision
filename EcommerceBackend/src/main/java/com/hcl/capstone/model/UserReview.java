package com.hcl.capstone.model;

import javax.persistence.CascadeType;
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
    public UserReview() {}
    public UserReview(User user, Product product, int score, String review) {
        super();
        this.user = user;
        this.product = product;
        this.score = score;
        this.review = review;
    }
    public UserReview(UserReviewDto userReviewDto) {
        super();
        this.user = userReviewDto.getDtoUser();
        this.product = userReviewDto.getProduct();
        this.score = userReviewDto.getScore();
        this.review = userReviewDto.getReview();
    }
    

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    private long reviewId;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User user;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "SCORE")
    private int score;

    @Column(name = "REVIEW")
    private String review;


}
