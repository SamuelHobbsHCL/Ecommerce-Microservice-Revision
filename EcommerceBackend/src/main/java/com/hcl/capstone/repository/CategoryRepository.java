package com.hcl.capstone.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.capstone.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository <Category, Integer>{
	public List<Category> findAll();
}
