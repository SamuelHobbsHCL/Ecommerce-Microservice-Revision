package com.hcl.capstone.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.hcl.capstone.model.User;
import com.hcl.capstone.repository.UserRepository;
import com.hcl.capstone.service.UserService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
	@InjectMocks
	private UserService userService;
	@Mock
	private UserRepository userRepository;	
	private User user;
	
	@BeforeAll
	void init() {
		MockitoAnnotations.openMocks(this);		
		user = new User("samhobbs","password","Sam","Hobbs","sam@email.com");
		user.setUserId(0);
	}
	
	@Test
	@Order(1)
	public void registerUser() throws Exception {
		when(userRepository.save(user)).thenReturn(user);
	}
	@Test
	@Order(2)
	public void getAllUsers() throws Exception {
		User user2 = new User("benhobbs","password","Ben","Hobbs","ben@email.com");
		User user3 = new User("nickhobbs","password","Nick","Hobbs","nick@email.com");
		List<User> users = Arrays.asList(user,user2,user3);
		
		when(userRepository.findAll()).thenReturn(users);
		List<User> foundUsers = userService.getAllUsers();
		
		assertEquals(3, foundUsers.size());
		assertEquals(users,foundUsers);
	}
	@Order(3)
	@Test
	public void getUserByEmailAndPassword() throws Exception {
		when(userRepository.findByEmailAndPassword(
				user.getEmail(),user.getPassword()))
			.thenReturn(user);
		User foundUser = userService.getUserbyEmailAndPassword(
				user.getEmail(),user.getPassword());
		assertEquals(foundUser,user);		
		
	}
	@Order(4)
	@Test
	public void getCurrentLoggedInUser() throws Exception {
		
	}
	@Order(5)
	@Test
	public void getUserByUserName() throws Exception {
		when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
		User foundUser = userService.getUserbyUserName(user.getUserName());
		assertEquals(foundUser,user);		
	}
	@Order(6)
	@Test
	public void getUserByEmail() throws Exception {
		when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
		User foundUser = userService.getUserbyEmail(user.getEmail());
		assertEquals(foundUser,user);	
	}
	@Test
	@Order(7)
	public void getUserById() throws Exception {
		when(userRepository.findById(user.getUserId())).thenReturn(user);
		User foundUser = userService.getUserById(user.getUserId());
		assertEquals(foundUser,user);	
	}	
	
	@Test
	@Order(8)
	public void sendRegistrationConfirmationEmail() throws Exception {
		
	}

	@Test
	@Order(9)
	public void updateAuthProvider() throws Exception {
		
	}
	@Test
	@Order(10)
	public void updateUser() throws Exception {
		
	}

	// Last test - delete user entered into database
	@AfterAll
	@Test
	public void deleteUserById() throws Exception {
		
	}
}
