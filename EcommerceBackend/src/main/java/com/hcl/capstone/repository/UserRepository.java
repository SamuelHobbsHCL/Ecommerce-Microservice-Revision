package com.hcl.capstone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcl.capstone.model.User;
import com.hcl.capstone.model.enumeration.AuthProvider;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	public User findById(long id);
	public List<User> findAll();
	public void deleteById(long id);
	public User findByEmailAndPassword(String email, String password);
	
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.userName = ?1")
	public User findByUserName(String userName);
	
	@Query("UPDATE User u SET u.authProvider = ?2 WHERE u.userId = ?1")
	@Modifying
	public void updateAuthProvider(long userId, AuthProvider authProvider);

}
