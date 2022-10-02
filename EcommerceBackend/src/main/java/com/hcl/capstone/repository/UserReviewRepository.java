package com.hcl.capstone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.capstone.model.Product;
import com.hcl.capstone.model.UserReview;

@Repository
public interface UserReviewRepository extends CrudRepository<UserReview, Long>{
	public UserReview findById(long id);
	public List<UserReview> findAll();
	public void deleteById(long id);
	public List<UserReview> findByProduct(Product product);
	public List<UserReview> findByUser(long userId);
	public float getAverageScoreByProduct(long productId);

}
 