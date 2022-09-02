package com.hcl.capstone.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hcl.capstone.security.jwt.CustomUserDetailsService;
import com.hcl.capstone.security.jwt.RestAuthenticationEntryPoint;
import com.hcl.capstone.security.jwt.StaticJwtAuthenticationProvider;
import com.hcl.capstone.security.jwt.TokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private TokenAuthenticationFilter tokenAuthenticationFilter;
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	private static final String[] SWAGGER_WHITELIST = { "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", };

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.authenticationProvider(staticJwtAuthenticationProvider());
		authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	StaticJwtAuthenticationProvider staticJwtAuthenticationProvider() {
		return new StaticJwtAuthenticationProvider();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and()
			.csrf().disable()
			.formLogin().disable()
			.httpBasic().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
				.antMatchers("/auth/**", "/oauth2/**", "/api/**").permitAll()
				.antMatchers(HttpHeaders.ALLOW).permitAll()
				.antMatchers(SWAGGER_WHITELIST).permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("ADMIN","admins")
				.antMatchers("/user/**").hasAnyAuthority("USER","users")
				.anyRequest()
				.authenticated()
				.and()
					.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
				.and()
					.oauth2ResourceServer().jwt();

		// Add our custom Token based authentication filter
		http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
