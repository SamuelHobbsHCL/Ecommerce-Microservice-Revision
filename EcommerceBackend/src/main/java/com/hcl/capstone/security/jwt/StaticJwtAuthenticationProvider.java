package com.hcl.capstone.security.jwt;

import java.nio.charset.StandardCharsets;

import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.oauth2.server.resource.BearerTokenErrorCodes;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


public class StaticJwtAuthenticationProvider implements AuthenticationProvider {
	
	private static final String SECRET_KEY = "alicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkeyalicesecretkey";
	
	private static final Logger logger = LoggerFactory.getLogger(StaticJwtAuthenticationProvider.class);

	private static final OAuth2Error DEFAULT_INVALID_TOKEN =
            invalidToken("An error occurred while attempting to decode the Jwt: Invalid token");

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    public JwtDecoder jwtDecoder() {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HS512");
        return NimbusJwtDecoder.withSecretKey(key).build();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info("Authenticating with custom AuthenticationProvider ...");
        BearerTokenAuthenticationToken bearerToken = (BearerTokenAuthenticationToken) authentication;

        String kid = null;
        try {
            DecodedJWT jwt = JWT.decode(bearerToken.getToken());
            kid = jwt.getKeyId();
        } catch (JWTDecodeException exception) {
            logger.info(exception.getMessage());
        }
        if (kid == null) {
            logger.info("JWT header does not contain a key ID / JWK Set URL. Provided token must be a static token.");
            logger.info("Trying to authenticate ...");

            BearerTokenAuthenticationToken bearer = (BearerTokenAuthenticationToken) authentication;
            String email;
            try {
        		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(bearer.getToken()).getBody();
        		email = claims.getSubject();
                //jwt = jwtDecoder().decode(bearer.getToken());
            } catch (JwtException failed) {
                OAuth2Error invalidToken = invalidToken(failed.getMessage());
                logger.info(invalidToken.getDescription());
                throw new OAuth2AuthenticationException(invalidToken, invalidToken.getDescription(), failed);
            }
            
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            AbstractAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
            
            logger.info("Successfully authenticated user with static token. (principal: {})", token.getPrincipal());
            return token;
        } else {
            logger.info("JWT header contains a key ID. Skipping static jwt verification ...");
            return null;
        }
    }

    private static OAuth2Error invalidToken(String message) {
        try {
            return new BearerTokenError(
                    BearerTokenErrorCodes.INVALID_TOKEN,
                    HttpStatus.UNAUTHORIZED,
                    message,
                    "https://tools.ietf.org/html/rfc6750#section-3.1");
        } catch (IllegalArgumentException malformed) {
            // some third-party library error messages are not suitable for RFC 6750's error message charset
            return DEFAULT_INVALID_TOKEN;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
//        return BearerTokenAuthenticationToken.class.isAssignableFrom(authentication);
        return authentication.equals(BearerTokenAuthenticationToken.class);
    }

}
