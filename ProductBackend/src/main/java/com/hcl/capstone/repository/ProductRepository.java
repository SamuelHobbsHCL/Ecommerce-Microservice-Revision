package com.hcl.capstone.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.capstone.model.Product;


@Repository
public interface ProductRepository extends JpaRepository <Product, Long>{
	
	public List<Product> findAll();
	public Product findById(long id);
	public Product deleteById(long id);
	public List<Product> findAllByProductNameContaining(String searchStr, Pageable pageable);
	public List<Product> findAllByProductNameContaining(String searchStr);
}
