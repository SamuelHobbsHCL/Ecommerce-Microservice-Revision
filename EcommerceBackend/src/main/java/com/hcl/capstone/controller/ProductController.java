package com.hcl.capstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.capstone.model.Product;
import com.hcl.capstone.service.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/api/products")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/api/product/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Product getProductById(@PathVariable(value = "id") long id){
		return productService.getProductById(id);
	}
	
	@GetMapping("/api/product/search")
	@CrossOrigin("http://localhost:4200")
	public List<Product> searchProducts(@RequestParam String searchStr, 
			@RequestParam int index, @RequestParam int count) {
		// Returns top <count> results starting from <index>
		return productService.searchProducts(searchStr, index, count);
	}
	
	@PostMapping("/inventory/add")
	@CrossOrigin(origins = "http://localhost:4200")
	public Product addProduct(@RequestBody Product product){
		return productsService.saveProduct(product);
	}
	
}
