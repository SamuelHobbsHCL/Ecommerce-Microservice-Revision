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

import com.hcl.capstone.mailer.Mail;
import com.hcl.capstone.model.Roles;
import com.hcl.capstone.model.User;
import com.hcl.capstone.model.enumeration.AuthProvider;
import com.hcl.capstone.repository.RoleRepository;
import com.hcl.capstone.repository.UserRepository;
import com.hcl.capstone.security.CustomUserDetails;

@Service
public class UserService {

	@Autowired
	private UserRepository usersRepository;

	@Autowired
	private RoleRepository roleRepository;

	public User getUserById(long id) {
		return usersRepository.findById(id);
	}

	public List<User> getAllUsers() {
		return usersRepository.findAll();
	}

	public User registerUser(User user) throws AddressException, MessagingException, IOException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());

		user.setPassword(encodedPassword);
		user.setAuthProvider(AuthProvider.LOCAL);

		Roles role = roleRepository.findByName("USER");
		Set<Roles> userRoles = new HashSet<>();
		userRoles.add(role);

		user.setRoles(userRoles);

		sendRegistrationConfirmationEmail(user);

		return usersRepository.save(user);
	}

	public void sendRegistrationConfirmationEmail(User user) {
		Mail mailer = new Mail();
		try {
			mailer.send(user);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteUserById(long id) {
		usersRepository.deleteById(id);
	}

	public User updateUser(User user, long id) {
		Optional<User> userRepo = Optional.ofNullable(usersRepository.findById(id));

		if (!userRepo.isPresent()) {
			return null;
		}

		user.setUserId(id);
		usersRepository.save(user);

		return usersRepository.findById(id);
	}

	// NOTE - not fully camel case (should be getUserByEmailAndPassword)
	public User getUserbyEmailAndPassword(String email, String password) {
		return usersRepository.findByEmailAndPassword(email, password);
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

			User user = getUserbyEmail(email);

			if (user == null) {
				System.out.print("Could not find user by email. Registering user in database.");

				User newUser = new User();
				newUser.setEmail(email);
				newUser.setUserName(email);
				newUser.setAuthProvider(AuthProvider.OKTA);
				String firstName = ((Jwt) principal).getClaimAsString("firstName");
				String lastName = ((Jwt) principal).getClaimAsString("lastName");
				newUser.setFirstName(firstName);
				newUser.setLastName(lastName);
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(newUser.getEmail() + newUser.getFirstName());
				newUser.setPassword(encodedPassword);

				List<String> groups = ((Jwt) principal).getClaimAsStringList("groups");

				Set<Roles> userRoles = new HashSet<>();

				for (String groupItem : groups) {
					if (groupItem.equals("admins")) {
						Roles role = roleRepository.findByName("ADMIN");
						userRoles.add(role);
					}

					if (groupItem.equals("users")) {
						Roles role = roleRepository.findByName("USER");
						userRoles.add(role);
					}
				}

				newUser.setRoles(userRoles);

				usersRepository.save(newUser);

				sendRegistrationConfirmationEmail(newUser);
			}

			return getUserbyEmail(email);
			
		} else {
			return null;
		}
	}

	public void updateAuthProvider(User user, AuthProvider type) {
		if (!user.getAuthProvider().equals(type)) {
			usersRepository.updateAuthProvider(user.getUserId(), type);
		}
	}

	// NOTE - not fully camel case
	public User getUserbyUserName(String userName) {
		return usersRepository.findByUserName(userName);
	}

	// NOTE - not fully camel case
	public User getUserbyEmail(String email) {
		return usersRepository.findByEmail(email);
	}

}
