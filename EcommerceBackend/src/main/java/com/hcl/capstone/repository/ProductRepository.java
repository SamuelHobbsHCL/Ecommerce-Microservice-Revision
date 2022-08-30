package com.hcl.capstone.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.capstone.model.Product;


@Repository
public interface ProductRepository extends CrudRepository <Product, Long>{
	
	public List<Product> findAll();
	public Product findById(long id);
	public void deleteById(long id);

}
