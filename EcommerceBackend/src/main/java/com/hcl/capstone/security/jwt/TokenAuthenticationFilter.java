package com.hcl.capstone.security.jwt;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hcl.capstone.model.Roles;
import com.hcl.capstone.model.User;
import com.hcl.capstone.model.enumeration.AuthProvider;
import com.hcl.capstone.repository.RoleRepository;
import com.hcl.capstone.repository.UserRepository;
import com.hcl.capstone.service.UserService;
import com.okta.jwt.JwtVerificationException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwt = getJwtFromRequest(request);

		if (StringUtils.hasText(jwt)) {
			if (tokenProvider.validateOktaToken(jwt)) {

				try {
					Map<String, Object> claimMaps;
					claimMaps = tokenProvider.getClaimsMap(jwt);
					String email = claimMaps.get("sub").toString();
					String firstName = claimMaps.get("firstName").toString();
					String lastName = claimMaps.get("lastName").toString();
					
					List<String> groupList = (List<String>) claimMaps.get("groups");
					
					if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
						UserDetails userDetails = null;
						
						try {
							userDetails = customUserDetailsService.loadUserByUsername(email);
						} catch (UsernameNotFoundException e) {
							System.out.print("Could not find user by email. Registering user in database.");

							User newUser = new User();
							newUser.setEmail(email);
							newUser.setUserName(email);
							newUser.setAuthProvider(AuthProvider.OKTA);
	
							newUser.setFirstName(firstName);
							newUser.setLastName(lastName);
							BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
							String encodedPassword = passwordEncoder.encode(newUser.getEmail() + newUser.getFirstName());
							newUser.setPassword(encodedPassword);

							Set<Roles> userRoles = new HashSet<>();

							for (String groupItem : groupList) {
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

							userRepository.save(newUser);

							userService.sendRegistrationConfirmationEmail(newUser);
						}
						
						userDetails = customUserDetailsService.loadUserByUsername(email);

						AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				} catch (JwtVerificationException e) {
					e.printStackTrace();
				}

			} else if (tokenProvider.validateBackendToken(jwt)) {
				String email = tokenProvider.getEmailFromBackendToken(jwt);

				if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}

		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
