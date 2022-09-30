package com.hcl.capstone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.capstone.model.UserReview;

@Repository
public interface UserReviewRepository extends CrudRepository<UserReview, Long>{
	public UserReview findById(long id);
	public List<UserReview> findAll();
	public void deleteById(long id);
	public List<UserReview> findByProductId(long id);
	public List<UserReview> findByUserId(long id);
	
	@Query("SELECT AVG(r.score) FROM USER_REVIEW WHERE r.product ?=1")
	public float getAverageScoreByProduct(long id);

}
