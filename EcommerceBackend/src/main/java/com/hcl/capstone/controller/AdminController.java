package com.hcl.capstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.capstone.model.Product;
import com.hcl.capstone.model.User;
import com.hcl.capstone.service.ProductService;
import com.hcl.capstone.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productsService;

	@DeleteMapping("/admin/delete-user/{id}")
	public void deleteUserById(@PathVariable(value = "id") long id) {
		userService.deleteUserById(id);
	}
	
	@GetMapping("/admin/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/admin/products")
	public List<Product> getAllProducts() {
		return productsService.getAllProducts();
	}
	
	@PostMapping("/admin/add-product")
	@ResponseStatus(HttpStatus.CREATED)
	public Product addProduct(@RequestBody Product product) {
		return productsService.saveProduct(product);
	}
	
	@DeleteMapping("/admin/product/{id}")
	public void deleteProductById(@PathVariable(value = "id") long id) {
		productsService.deleteProductById(id);
	}
	
	@GetMapping("/admin/user/{id}")
	public User getUserById(@PathVariable(value = "id") long id){
		return userService.getUserById(id);
	}
	
	@PutMapping("/admin/user/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long id){
		
		User result = userService.updateUser(user, id);
		
		if(result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@PutMapping("/admin/product/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable int id){
		
		Product result = productsService.updateProduct(product, id);
		
		if(result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
}
