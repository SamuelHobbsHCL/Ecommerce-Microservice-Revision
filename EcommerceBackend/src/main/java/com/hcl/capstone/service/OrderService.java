package com.hcl.capstone.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.hcl.capstone.dto.OrderInfo;
import com.hcl.capstone.global.OrderStatus;
import com.hcl.capstone.mailer.Mail;
import com.hcl.capstone.model.Address;
import com.hcl.capstone.model.Order;
import com.hcl.capstone.model.OrderItem;
import com.hcl.capstone.model.Product;
import com.hcl.capstone.model.User;
import com.hcl.capstone.repository.OrderItemRepository;
import com.hcl.capstone.repository.OrderRepository;
import com.hcl.capstone.repository.ProductRepository;

@Service
public class OrderService {	
	@Autowired private OrderItemRepository orderItemRepository;
	@Autowired private OrderRepository orderRepository;
	@Autowired private ProductRepository productsRepository;
	@Autowired private UserService userService;
	@Autowired private ProductService productService;
		
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public List<Order> getAllOrderByUser(User user) {
		return orderRepository.findByUser(user);
	}
	
	public List<OrderItem> getAllOrderItemsByUserAndOrder(User user, Order order) {
		return orderItemRepository.findByUserAndOrder(user, order);
	}
	
	public List<OrderItem> addProductToCart(long productId, int quantity, Authentication authentication) {
		User user = userService.getCurrentLoggedInUser(authentication);

		List<Order> orders = getAllOrderByUser(user);

		long orderId = -1;

		for (Order order : orders) {
			if (order.getOrderStatus().equals(OrderStatus.IN_PROGRESS)) {
				orderId = order.getOrderId();
			}
		}

		Order order = getOrderDetail(orderId);

		if (order == null) {
			order = new Order();
			Date date = new Date();
			order.setOrderDate(date);
			order.setOrderStatus(OrderStatus.IN_PROGRESS);
			order.setUser(user);
			// Billing & shipping address are not set until checkout
			order.setBillingAddress(null);
			order.setShippingAddress(null);
			saveOrder(order);
		}

		addProduct(productId, quantity, user, order);

		double orderTotal = getOrderTotal(user, order);
		order.setOrderTotal(orderTotal);

		updateOrder(order);

		return getAllOrderItemsByUserAndOrder(user, order);
		
	}
	
	public void addProduct(long productId, int quantity, User user, Order order) {
		int addedQuantity = quantity;
		
		Product product = productsRepository.findById(productId);
		
		OrderItem orderItem = orderItemRepository.findByUserAndProductAndOrder(user, product, order);
		
		if(orderItem != null) {
			addedQuantity = orderItem.getQuantity() + quantity;
			orderItem.setQuantity(addedQuantity);
		} else {
			orderItem = new OrderItem();
			orderItem.setQuantity(quantity);
			orderItem.setUser(user);
			orderItem.setProduct(product);
			orderItem.setOrder(order);
		}
		
		orderItemRepository.save(orderItem);
	}
	
	public String checkOut(OrderInfo orderInfo, Authentication authentication)
			throws MessagingException {
		User userCheckout = userService.getCurrentLoggedInUser(authentication);
		if (userCheckout != null) {

			Order orderCheckout = getOrderInProgress(authentication);

			if (orderCheckout.getOrderStatus().equals(OrderStatus.IN_PROGRESS)) {
				List<OrderItem> itemsCheckout = getAllOrderItemsByUserAndOrder(userCheckout,
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

					productService.saveProduct(productCheckout);
				}

				double orderTotal = getOrderTotal(userCheckout, orderCheckout);
				orderCheckout.setOrderTotal(orderTotal);
				orderCheckout.setOrderStatus(OrderStatus.COMPLETED);
				orderCheckout.setOrderDate(new Date());
				// Set billing & shipping address
				orderCheckout.setShippingAddress(new Address(orderInfo.getShippingAddress()));
				orderCheckout.setBillingAddress(new Address(orderInfo.getBillingAddress()));
				saveOrder(orderCheckout);

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
	
	public double getOrderTotal(User user, Order order) {
		double total = 0f;
		
		List<OrderItem> orderItems = orderItemRepository.findByUserAndOrder(user, order);
		
		for(OrderItem orderItem : orderItems) {
			double productPrice = orderItem.getProduct().getUnitPrice();
			total += orderItem.getQuantity() * productPrice;
		}
		
		return total;
	}
	
		
	public Order getOrderDetail(long orderId) {
		Optional<Order> order = orderRepository.findById(orderId); 
		return order.isPresent() ? order.get() : null;
	}
	
	public Optional<Order> updateOrder(Order order) {
		long orderId = order.getOrderId();
		Optional<Order> orderRepo = orderRepository.findById(orderId);
		
		if(!orderRepo.isPresent()) {
			return Optional.empty();
		}
		
		order.setOrderId(orderId);
		orderRepository.save(order);
		
		return orderRepository.findById(orderId);
	}
		
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public List<OrderItem> getOrderItemByOrder(Order order) {
		return orderItemRepository.findByOrder(order);
	}
	
	public void deleteAllOrderItemsByOrderId(long orderId) {
		Order order =  getOrderDetail(orderId);
		List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
		for(OrderItem item : orderItems) {
			orderItemRepository.delete(item);
		}
	}
	
	public void deleteOrderItemById(long orderItemId, Authentication authentication) {
		orderItemRepository.deleteById(orderItemId);
		Order order = getOrderInProgress(authentication);
		User user = userService.getCurrentLoggedInUser(authentication);
		double orderTotal = getOrderTotal(user, order);
		order.setOrderTotal(orderTotal);
		updateOrder(order);
	}	
	
	public Order getOrderInProgress(Authentication authentication) {
		User user = userService.getCurrentLoggedInUser(authentication);

		List<Order> orders = getAllOrderByUser(user);

		Order orderInProgress = null;

		for (Order order : orders) {
			if (order.getOrderStatus().equals(OrderStatus.IN_PROGRESS)) {
				orderInProgress = order;
			}
		}
		
		return orderInProgress;
	}
}
