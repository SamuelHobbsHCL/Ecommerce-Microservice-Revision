package com.hcl.capstone.security.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.okta.jwt.JwtVerificationException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

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
					Object claimSubject = claimMaps.get("sub");
					String email = claimSubject.toString();

					List<String> groupList = (List<String>) claimMaps.get("groups");
//					for (String role : groupList) {
//						if (role.equals("admins")) {
//							System.out.println("Has admin access");
//						} else {
//							System.out.println("Has user access");
//
//						}
//					}
					
//					if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//						
//						UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
//						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//								userDetails, null, userDetails.getAuthorities());
//						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//						SecurityContextHolder.getContext().setAuthentication(authentication);
//					}
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
