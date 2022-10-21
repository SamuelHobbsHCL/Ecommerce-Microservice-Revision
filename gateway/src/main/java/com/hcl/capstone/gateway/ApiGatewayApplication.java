package com.hcl.capstone.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	private String productApplUri = "http://localhost:8081";
	private String ecommerceAppUri = "http://localhost:8082";
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/product/**")
						.filters(f -> f
								.rewritePath("/(?<base>.*?)/(?<segment>.*)","/$\\{segment}")
								.setResponseHeader("Access-Control-Allow-Origin", "http://localhost:8080"))
						.uri(productApplUri))
				.route(p -> p
						.path("/ecomm/**")
						.filters(f -> f.rewritePath("/(?<base>.*?)/(?<segment>.*)","/$\\{segment}")
								.setResponseHeader("Access-Control-Allow-Origin", "http://localhost:8080"))
						.uri(ecommerceAppUri))
				.build();
	}
	
}
