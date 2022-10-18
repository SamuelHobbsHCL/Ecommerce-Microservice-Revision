package com.hcl.capstone.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.capstone.model.Order;
import com.hcl.capstone.model.User;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
	
	public List<Order> findByUser(User user);
	public List<Order> findAll();
	public Order findById(long id);
	
	@Transactional
	@Modifying
	@Query("update Order o set o.orderStatus = ?1 where o.orderId = ?2")
	public int updateOrderStatus(String status, long id);

}
