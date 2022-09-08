package com.hcl.capstone.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.capstone.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
	
	public Address findById(long id);

}
