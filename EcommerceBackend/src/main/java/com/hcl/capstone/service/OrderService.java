package com.hcl.capstone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.hcl.capstone.model.Order;
import com.hcl.capstone.model.OrderItem;
import com.hcl.capstone.model.Product;
import com.hcl.capstone.model.User;
import com.hcl.capstone.repository.OrderItemRepository;
import com.hcl.capstone.repository.OrderRepository;
import com.hcl.capstone.repository.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productsRepository;
	
	@Autowired
	private UserService usersService;
	
	public List<OrderItem> listCartItems(User user) {
		return orderItemRepository.findByUser(user);
	}
	
	//
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}
	
	public List<Order> getAllOrderByUser(User user) {
		return orderRepository.findByUser(user);
	}
	
	public List<OrderItem> getAllOrderItemsByUserAndOrder(User user, Order order) {
		return orderItemRepository.findByUserAndOrder(user, order);
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
			return null;
		}
		
		order.setOrderId(orderId);
		orderRepository.save(order);
		
		return orderRepository.findById(orderId);
	}
	
	
	public double getCartAmount(List<OrderItem> cartList) {
		double totalCartAmount = 0f;
		double singleCartAmount = 0f;
		int availableQuantity = 0;
		
		for(OrderItem cart : cartList) {
			long productId = cart.getProduct().getProductId();
			Optional<Product> product = Optional.ofNullable(productsRepository.findById(productId));
			if(product.isPresent()) {
				Product currentProduct = product.get();
				if(currentProduct.getProductStock() < cart.getQuantity()) {
					singleCartAmount = currentProduct.getUnitPrice() * currentProduct.getProductStock();
					cart.setQuantity(currentProduct.getProductStock());
				} else {
					singleCartAmount = cart.getQuantity() * currentProduct.getUnitPrice();
					availableQuantity = currentProduct.getProductStock() - cart.getQuantity();
				}
				
				totalCartAmount += singleCartAmount;
				currentProduct.setProductStock(availableQuantity);
				availableQuantity = 0;
				productsRepository.save(currentProduct);
				
			}
		}
		return totalCartAmount;
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
	
	public void deleteOrderItemById(long orderItemId) {
		orderItemRepository.deleteById(orderItemId);
	}
	
	public Order getOrderInProgress(Authentication authentication) {
		User user = usersService.getCurrentLoggedInUser(authentication);

		List<Order> orders = getAllOrderByUser(user);

		Order orderInProgress = null;

		for (Order order : orders) {
			if (order.getOrderStatus().equals("In Progress")) {
				orderInProgress = order;
			}
		}
		
		return orderInProgress;
	}
}
