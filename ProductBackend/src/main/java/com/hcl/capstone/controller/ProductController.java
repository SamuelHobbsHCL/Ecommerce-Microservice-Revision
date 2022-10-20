package com.hcl.capstone.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.capstone.dto.ProductDto;
import com.hcl.capstone.dto.UpdateImageDto;
import com.hcl.capstone.model.Category;
import com.hcl.capstone.model.Product;
import com.hcl.capstone.service.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "http://localhost:8081")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/api/products")
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/api/product/{id}")
	public Product getProductById(@PathVariable(value = "id") long id){
		return productService.getProductById(id);
	}
	
	@GetMapping("/api/product/search/page")
	public List<Product> searchProductsPages(@RequestParam String searchStr, 
			@RequestParam int index, @RequestParam int count) {
		// Returns top <count> results starting from <index>
		return productService.searchProducts(searchStr, index, count);
	}

	@GetMapping("/api/product/search")
	public List<Product> searchProducts(@RequestParam String searchStr) {
		// Returns all matching results
		return productService.searchProducts(searchStr);
	}
	
	@GetMapping("/api/product/categories")
	public List<Category> getProductCategories() {
		return productService.getAllCategories();
	}
	
	@PutMapping("/api/update-product-image/{id}")
    public ResponseEntity<String> updateImage(@RequestBody UpdateImageDto updateImageDTO, @PathVariable long id) {
    	if(StringUtils.isNotEmpty(updateImageDTO.getImageUrl())) {
    		productService.updateProductImage(id, updateImageDTO.getImageUrl());
    		
    		return new ResponseEntity<>("Image saved successfully", HttpStatus.OK);    		
    	} else {
    		return new ResponseEntity<>("Please provide image url", HttpStatus.BAD_REQUEST);
    	}		
    }
	
	// Admin only
	@GetMapping("/admin/products")
	public List<Product> getAllProductsAdmin() {
		return productService.getAllProducts();
	}

	@PostMapping("/admin/add-product")
	@ResponseStatus(HttpStatus.CREATED)
	public Product addProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@DeleteMapping("/admin/delete-product/{id}")
	public Product deleteProductById(@PathVariable(value = "id") long id) {
		return productService.deleteProductById(id);
	}
	
	@PutMapping("/admin/product")
	public ResponseEntity<Product> updateProduct(@RequestBody ProductDto productDTO){		
		Product result = productService.updateProduct(productDTO);
		
		if(result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	// New
	@PutMapping("/api/product/update-stock/{id}")
	public Product updateProductStock(@PathVariable long id, @RequestParam int stock) {
		return productService.updateProductStock(id,stock);
	}
	
	// Revised from original (api path changed as well)
	@PutMapping("/api/product/update-image/{id}")
	public Product updateProductImage(@PathVariable long id, @RequestParam String imageUrl) {
		return productService.updateProductImage(id,imageUrl);
	}
}
