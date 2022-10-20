package com.hcl.capstone.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

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
import com.hcl.capstone.dto.AddressDto;
import com.hcl.capstone.dto.OrderDto;
import com.hcl.capstone.dto.ProductDto;
import com.hcl.capstone.model.Address;
import com.hcl.capstone.model.Order;
import com.hcl.capstone.service.ProductService;
import com.hcl.capstone.service.UserService;
import com.hcl.capstone.service.OrderService;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productsService;
	
	@Autowired
	private OrderService orderService;

	@DeleteMapping("/admin/delete-user/{id}")
	public void deleteUserById(@PathVariable(value = "id") long id) {
		userService.deleteUserById(id);
	}
	
	@GetMapping("/admin/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	// Admin not currently needed by Product microservice, so bearer token not required
	@GetMapping("/admin/products")
	public List<Product> getAllProducts() {
		return productsService.getAllProducts();
	}
	
	@GetMapping("/admin/orders")
	public List<Order> getAllOrders(){
		return orderService.getAllOrders();
	}
	
	@PostMapping("/admin/add-product")
	@ResponseStatus(HttpStatus.CREATED)
	public Product addProduct(HttpServletRequest request, @RequestBody Product product) {
		return productsService.addProduct(product, request.getHeader("Authorization"));
	}
	
	@DeleteMapping("/admin/delete-product/{id}")
	public void deleteProductById(HttpServletRequest request, @PathVariable(value = "id") long id) {
		productsService.deleteProductById(id, request.getHeader("Authorization"));
	}
	
	@GetMapping("/admin/user/{id}")
	public User getUserById(@PathVariable(value = "id") long id){
		return userService.getUserById(id);
	}
	
	@GetMapping("/admin/order/{id}")
	public Order getOrderById(@PathVariable(value = "id") long id){
		return orderService.getOrderDetail(id);
	}
	
	@GetMapping("/admin/address/{userId}")
	public Address getAddressById(@PathVariable long userId) {
		return userService.getUserAddress(userId);
	}
	
	@PutMapping("/admin/update-address/{userId}")
	public Address updateAddress(@PathVariable long userId, @RequestBody AddressDto addressDTO) {
		return userService.updateAddress(userId, addressDTO);
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
	
	@PutMapping("/admin/product")
	public ResponseEntity<Product> updateProduct(HttpServletRequest request, @RequestBody ProductDto productDTO){
		
		Product result = productsService.updateProduct(productDTO, request.getHeader("Authorization"));
		
		if(result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@PutMapping("/admin/order/update/{id}")
	public ResponseEntity<Order> updateOrder(@RequestBody OrderDto orderDTO, @PathVariable long id) throws MessagingException{
		Order result = orderService.updateOrderStatus(orderDTO, id);
		if(result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
}
