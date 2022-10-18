package com.hcl.capstone.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.capstone.model.Order;
import com.hcl.capstone.model.OrderItem;
import com.hcl.capstone.model.Product;
import com.hcl.capstone.model.User;


@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

	public List<OrderItem> findByUser(User user);
	
	public OrderItem findByUserAndProductAndOrder(User user, Product product, Order order);
	
	public List<OrderItem> findByUserAndOrder(User user, Order order);
	 
	public List<OrderItem> findByOrder(Order order);

}
