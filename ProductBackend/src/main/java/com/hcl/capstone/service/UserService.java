package com.hcl.capstone.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.hcl.capstone.dto.AddressDto;
import com.hcl.capstone.dto.PasswordDto;
import com.hcl.capstone.global.RoleName;
import com.hcl.capstone.mailer.Mail;
import com.hcl.capstone.model.Address;
import com.hcl.capstone.model.Roles;
import com.hcl.capstone.model.User;
import com.hcl.capstone.model.enumeration.AuthProvider;
import com.hcl.capstone.repository.RoleRepository;
import com.hcl.capstone.repository.UserRepository;
import com.hcl.capstone.security.CustomUserDetails;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public User getUserById(long id) {
		return userRepository.findById(id);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User registerUser(User user) throws AddressException, MessagingException, IOException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());

		user.setPassword(encodedPassword);
		user.setAuthProvider(AuthProvider.LOCAL);

		Roles role = roleRepository.findByName(RoleName.USER);
		Set<Roles> userRoles = new HashSet<>();
		userRoles.add(role);

		user.setRoles(userRoles);
		// Default address is null, may be set in their profile page
		user.setAddress(null);

		sendRegistrationConfirmationEmail(user);

		return userRepository.save(user);
	}

	private void sendRegistrationConfirmationEmail(User user) {
		Mail mailer = new Mail();
		try {
			mailer.send(user);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public boolean deleteUser(Authentication authentication) {
		User currentUser = getCurrentLoggedInUser(authentication);
		
		if(currentUser != null) {
			userRepository.deleteById(currentUser.getUserId());
			return true;
		} else {
			return false;
		}

	}
	
	public void deleteUserById(long id) {
		userRepository.deleteById(id);
	}
	
	
	public boolean updateImage(Authentication authentication, String imageUrl) {
		User currentUser = getCurrentLoggedInUser(authentication);
		if(currentUser != null) {
			currentUser.setProfileImage(imageUrl);
			userRepository.save(currentUser);
			return true;
		} else {
			return false;
		}
	}

	public User updateUser(User user, long id) {
		Optional<User> userRepo = Optional.ofNullable(userRepository.findById(id));

		if (!userRepo.isPresent()) {
			return null;
		}
		
		user.setUserId(id);
		user.setAuthProvider(user.getAuthProvider());
		if(user.getRoles() == null) {
			Roles role = roleRepository.findByName(RoleName.USER);
			Set<Roles> userRoles = new HashSet<>();
			userRoles.add(role);
	
			user.setRoles(userRoles);
		} else {
			user.setRoles(user.getRoles());
		}

		userRepository.save(user);

		return userRepository.findById(id);
	}

	public User getUserbyEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	public User getCurrentLoggedInUser(Authentication authentication) {
		if (authentication == null)
			return null;

		String email = null;
		Object principal = authentication.getPrincipal();

		if (principal != null) {
			if (principal instanceof Jwt) {
				email = ((Jwt) principal).getSubject();
			} else {
				email = ((CustomUserDetails) principal).getUsername();
			}

			return getUserbyEmail(email);
			
		} else {
			return null;
		}
	}

	public User getUserbyUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public User getUserbyEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Address getUserAddress(Authentication authentication) {		
		User user = getCurrentLoggedInUser(authentication);	
		return user.getAddress();
	}
	
	public Address getUserAddress(long id) {
		User user = getUserById(id);
		if (user != null)
			return user.getAddress();
		else
			return null;
	}
	
	public Address updateAddress(Authentication authentication, AddressDto addressDTO) {				
		User currentUser = getCurrentLoggedInUser(authentication);
		currentUser.setAddressByDto(addressDTO);
		
		userRepository.save(currentUser);
		
		return currentUser.getAddress();
	}
	
	public Address updateAddress(long userId, AddressDto addressDTO) {
		User user = getUserById(userId);
		if (user == null)
			return null;
		user.setAddressByDto(addressDTO);
		userRepository.save(user);
		return user.getAddress();
	}
	
	//update user password
	public boolean updateUserPassword(PasswordDto passwordDTO, Authentication authentication) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();	
		
		User user = getCurrentLoggedInUser(authentication);
		
		if(passwordEncoder.matches(passwordDTO.getCurrentPassword(), user.getPassword())) {
			String encodedNewPassword = passwordEncoder.encode(passwordDTO.getNewPassword());
			user.setPassword(encodedNewPassword);
			userRepository.save(user);
			return true;
		} else {
			return false;
		}
		
	}

}
