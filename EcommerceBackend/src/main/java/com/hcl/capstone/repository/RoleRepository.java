package com.hcl.capstone.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.capstone.model.Roles;

@Repository
public interface RoleRepository extends CrudRepository<Roles, Long>{

	@Query("SELECT r FROM Roles r WHERE r.roleName = ?1")
	public Roles findByName(String roleName);
}
