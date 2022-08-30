package com.hcl.capstone.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.capstone.model.Order;
import com.hcl.capstone.model.User;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
	
	public List<Order> findByUser(User user);

}
