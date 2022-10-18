package com.hcl.capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.hcl.capstone.security.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class EcommerceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
