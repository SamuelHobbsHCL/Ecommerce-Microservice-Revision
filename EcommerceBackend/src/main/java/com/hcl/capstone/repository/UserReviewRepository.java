package com.hcl.capstone.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.capstone.model.UserReview;

@Repository
public interface UserReviewRepository extends CrudRepository<UserReview, Long>{
	public UserReview findById(long id);
	public List<UserReview> findAll();
	public void deleteById(long id);
	public List<UserReview> findByProductId(long id);

}
