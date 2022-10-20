package com.hcl.capstone.test.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.capstone.controller.ProductController;
import com.hcl.capstone.security.jwt.TokenProvider;
import com.hcl.capstone.service.ProductService;

public class ProductControllerTest {
    private MockMvc mockMvc;
    @Mock private ProductService productService;
    @Mock TokenProvider tokenProvider;
    @InjectMocks private ProductController productController;    

    //Taken from https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
    public static String jsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
	@BeforeAll
	public void init() {
		
	}
	
	@Test
	public void getAllProducts() {}
	@Test
	public void getProductById() {}
	@Test
	public void searchProducts() {}
	@Test
	public void searchProductsPaging() {}
	@Test
	public void getProductCategories() {}
	@Test
	public void updateProductImage() {}
	@Test
	public void addProduct() {}
	@Test
	public void deleteProductById() {}
	@Test
	public void updateProduct() {}
	@Test
	public void updateProductStock() {}
}
