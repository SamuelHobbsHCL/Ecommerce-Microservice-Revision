package com.hcl.capstone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.hcl.capstone.dto.ProductDto;
import com.hcl.capstone.model.Product;
import com.hcl.capstone.model.User;
import com.hcl.capstone.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public Product getProductById(long id) {
		return productRepository.findById(id);
	}
	
	public void deleteProductById(long id) {
		productRepository.deleteById(id);
	}
	
	public Product updateProduct(ProductDto productDTO, long id) {
		Optional<Product> productRepo = Optional.ofNullable(productRepository.findById(id));
		
		if(!productRepo.isPresent()) {
			return null;
		}
		Product update = new Product(productDTO);
		update.setProductId(id);
		productRepository.save(update);
		
		return productRepository.findById(id);
	}
	
	public List<Product> searchProducts(String searchStr, int index, int count) {
		Pageable pageable = PageRequest.of(index, count);
		// Returns top <count> results starting from <index>
		return productRepository.findAllByProductNameContaining(searchStr,pageable);
	}
	
	public List<Product> searchProducts(String searchStr) {
		return productRepository.findAllByProductNameContaining(searchStr);
	}

	public boolean updateProductImage(ProductDto productDTO, String imageUrl) {
		Optional<Product> product = productRepository.findById(productDTO.getDtoId());
		if(!product.isPresent()) {
			return false;
		} else {
			Product update = new Product(productDTO);
			productRepository.save(update);
			return true;
		}
		
	}

}
