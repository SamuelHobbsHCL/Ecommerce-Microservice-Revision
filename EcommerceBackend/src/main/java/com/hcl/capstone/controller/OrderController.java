package com.hcl.capstone.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.capstone.mailer.Mail;
import com.hcl.capstone.model.Order;
import com.hcl.capstone.model.OrderItem;
import com.hcl.capstone.model.Product;
import com.hcl.capstone.model.User;
import com.hcl.capstone.service.OrderService;
import com.hcl.capstone.service.ProductService;
import com.hcl.capstone.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class OrderController {
	@Autowired private OrderService orderService;
	@Autowired private UserService userService;

	@PostMapping("/user/add-to-cart/{productId}/{quantity}")
	public List<OrderItem> addProductToCart(@PathVariable("productId") long productId,
			@PathVariable("quantity") int quantity, Authentication authentication) {
		return orderService.addProductToCart(productId, quantity, authentication);
	}

	@GetMapping(value = "/user/get-order")
	public List<Order> getOrderDetail(Authentication authentication) {
		User user = userService.getCurrentLoggedInUser(authentication);
		return orderService.getAllOrderByUser(user);
	}

	@GetMapping(value = "/user/get-order-in-progress")
	public Order getOrderInProgress(Authentication authentication) {
		return orderService.getOrderInProgress(authentication);
	}

	@GetMapping(value = "/user/get-order-items/{orderId}")
	public List<OrderItem> getOrderItemsByOrderId(@PathVariable("orderId") long orderId) {
		Order order = orderService.getOrderDetail(orderId);
		return orderService.getOrderItemByOrder(order);
	}

	@PostMapping(value = "/user/check-out")
	public String checkOut(Authentication authentication)
			throws AddressException, MessagingException, IOException {
		return orderService.checkOut(authentication);
	}

	@DeleteMapping(value = "/user/delete-all-order-items")
	public void deleteAllOrderItemsByOrderId(Authentication authentication) {
		Order order = orderService.getOrderInProgress(authentication);
		orderService.deleteAllOrderItemsByOrderId(order.getOrderId());
	}

	@DeleteMapping(value = "/user/delete-order-item/{id}")
	public void deleteOrderItemById(@PathVariable("id") long id, Authentication authentication) {
		orderService.deleteOrderItemById(id, authentication);
	}
}
