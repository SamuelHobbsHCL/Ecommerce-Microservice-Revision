package com.hcl.capstone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.capstone.model.Product;
import com.hcl.capstone.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productsRepository;
	
	public Product saveProduct(Product product) {
		return productsRepository.save(product);
	}
	
	public List<Product> getAllProducts() {
		return productsRepository.findAll();
	}
	
	public Product getProductById(long id) {
		return productsRepository.findById(id);
	}
	
	public void deleteProductById(long id) {
		productsRepository.deleteById(id);
	}
	
	public Product updateProduct(Product product, long id) {
		Optional<Product> productRepo = Optional.ofNullable(productsRepository.findById(id));
		
		if(!productRepo.isPresent()) {
			return null;
		}
		
		product.setProductId(id);
		productsRepository.save(product);
		
		return productsRepository.findById(id);
	}
	
	

}
