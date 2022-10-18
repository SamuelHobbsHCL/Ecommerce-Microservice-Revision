package com.hcl.capstone.security.jwt;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hcl.capstone.model.User;
import com.hcl.capstone.model.payload.AuthResponse;
import com.hcl.capstone.model.payload.LoginRequest;
import com.hcl.capstone.service.UserService;
import com.okta.jwt.AccessTokenVerifier;
import com.okta.jwt.Jwt;
import com.okta.jwt.JwtVerificationException;
import com.okta.jwt.JwtVerifiers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenProvider {

	private static final String SECRET_KEY = "alicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkey";

	private static final int TOKEN_VALIDITY = 3600 * 5;

	private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private UserService userService;

	public AuthResponse createToken(LoginRequest loginRequest) throws Exception {
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		authenticate(email, password);
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
		String newToken = generateToken(email);
		User user = userService.getUserbyEmail(email);

//		if (authentication instanceof OAuth2AuthenticationToken) {
//			DefaultOidcUser oauthUser = (DefaultOidcUser) authentication.getPrincipal();
//			email = oauthUser.getName();
//		} else {
//			CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//			email = customUserDetails.getEmail();
//		}

		return new AuthResponse(user, newToken);
	}

	public String getEmailFromOktaToken(String token) throws JwtVerificationException {
		Map<String, Object> claimMaps = getClaimsMap(token);
		Object claimSubject = claimMaps.get("sub");
		String email = claimSubject.toString();

		return email;
	}

	public String getEmailFromBackendToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

	public boolean validateOktaToken(String authToken) {
		try {
			try {
				getClaimsMap(authToken);
				return true;
			} catch (JwtVerificationException e) {
				// TODO Auto-generated catch block
				return false;
			}

		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		} catch (IncorrectClaimException ex) {
			System.out.println("Not Okta token.");
			return true;
		}
		return false;
	}

	public boolean validateBackendToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		} catch (IncorrectClaimException ex) {
			logger.error("Not Backend token");
		}
		return false;

	}

	public Map<String, Object> getClaimsMap(String token) throws JwtVerificationException {

		AccessTokenVerifier jwtVerifier = JwtVerifiers.accessTokenVerifierBuilder()
				.setIssuer("https://dev-06861319.okta.com/oauth2/default").setAudience("api://default") // defaults to
																										// 'api://default'
				.setConnectionTimeout(Duration.ofSeconds(1)) // defaults to 1s
				.build();

		Jwt jwt = jwtVerifier.decode(token);
		Map<String, Object> claimMap = jwt.getClaims();
		return claimMap;
	}

	public String generateToken(String email) {

		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(email)
				.setIssuer("https://static.token.generator.local")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}
	
	private void authenticate(String email, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
