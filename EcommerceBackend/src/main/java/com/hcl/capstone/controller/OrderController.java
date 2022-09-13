package com.hcl.capstone.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

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
	final String IN_PROGRESS = "In Progress";
	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productsService;

	@Autowired
	private UserService userService;

	@PostMapping("/user/add-to-cart/{productId}/{quantity}")
	public List<OrderItem> addProductToCart(@PathVariable("productId") long productId,
			@PathVariable("quantity") int quantity, Authentication authentication) {

		User user = userService.getCurrentLoggedInUser(authentication);

		List<Order> orders = orderService.getAllOrderByUser(user);

		long orderId = -1;

		for (Order order : orders) {
			if (order.getOrderStatus().equals(IN_PROGRESS)) {
				orderId = order.getOrderId();
			}
		}

		Order order = orderService.getOrderDetail(orderId);

		if (order == null) {
			order = new Order();
			Date date = new Date();
			order.setOrderDate(date);
			order.setOrderStatus(IN_PROGRESS);
			order.setUser(user);
			order.setBillingAddressId(1);
			order.setShippingAddressId(1);
			orderService.saveOrder(order);
		}

		orderService.addProduct(productId, quantity, user, order);

		double orderTotal = orderService.getOrderTotal(user, order);
		order.setOrderTotal(orderTotal);

		orderService.updateOrder(order);

		return orderService.getAllOrderItemsByUserAndOrder(user, order);
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
			throws MessagingException, IOException {
		User userCheckout = userService.getCurrentLoggedInUser(authentication);
		if (userCheckout != null) {

			Order orderCheckout = orderService.getOrderInProgress(authentication);

			if (orderCheckout.getOrderStatus().equals(IN_PROGRESS)) {
				List<OrderItem> itemsCheckout = orderService.getAllOrderItemsByUserAndOrder(userCheckout,
						orderCheckout);

				for (OrderItem itemCheckout : itemsCheckout) {
					Product productCheckout = itemCheckout.getProduct();

					if (itemCheckout.getQuantity() <= productCheckout.getProductStock()) {
						int currentStock = productCheckout.getProductStock() - itemCheckout.getQuantity();
						productCheckout.setProductStock(currentStock);
					} else {
						itemCheckout.setQuantity(productCheckout.getProductStock());
						productCheckout.setProductStock(0);
					}

					productsService.saveProduct(productCheckout);
				}

				double orderTotal = orderService.getOrderTotal(userCheckout, orderCheckout);
				orderCheckout.setOrderTotal(orderTotal);
				orderCheckout.setOrderStatus("COMPLETED");
				orderCheckout.setOrderDate(new Date());
				orderService.saveOrder(orderCheckout);

				Mail mailer = new Mail();
				mailer.sendCheckoutConfirmation(userCheckout, orderCheckout, itemsCheckout);

				return "Your order is successfully completed. Thank you for your purchase!";
			} else {
				return "Your order is already checkout. Please enter another order!";
			}
		} else {
			return "Not logged in";
		}

	}

	@DeleteMapping(value = "/user/delete-all-order-items")
	public void deleteAllOrderItemsByOrderId(Authentication authentication) {
		Order order = orderService.getOrderInProgress(authentication);
		orderService.deleteAllOrderItemsByOrderId(order.getOrderId());
	}

	@DeleteMapping(value = "/user/delete-order-item/{id}")
	public void deleteOrderItemById(@PathVariable("id") long id, Authentication authentication) {
		orderService.deleteOrderItemById(id);
		Order order = orderService.getOrderInProgress(authentication);
		User user = userService.getCurrentLoggedInUser(authentication);
		double orderTotal = orderService.getOrderTotal(user, order);
		order.setOrderTotal(orderTotal);
		orderService.updateOrder(order);
	}

}
