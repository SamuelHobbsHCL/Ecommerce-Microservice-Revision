package com.hcl.capstone.test.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.capstone.controller.AuthController;
import com.hcl.capstone.model.User;
import com.hcl.capstone.security.jwt.TokenProvider;
import com.hcl.capstone.service.UserService;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthControllerTest {
    private MockMvc mockMvc;
    @Mock private UserService userService;
    @Mock TokenProvider tokenProvider;
    @InjectMocks private AuthController authController;
    User user;

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
    	user = new User("samhobbs","password","Sam","Hobbs","sam@email.com"); 
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
	}
    
    // Register user
    @Test
    @Order(1)
    public void registerUser() throws Exception {
        when(userService.registerUser(ArgumentMatchers.any())).thenReturn(user);

        mockMvc.perform(post("/auth/signup")
                .content(jsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName",Matchers.equalTo("samhobbs")))    // TODO match user just created
                .andDo(print())
                .andReturn();
    }

    // Getting token from logging in
    /*
     * @Test
     * 
     * @Order(2) public void authenticateUser() throws Exception { LoginRequest
     * loginRequest = new LoginRequest("sam@email.com","password"); AuthResponse
     * authResponse = new AuthResponse(user,"SAMPLE_TOKEN");
     * when(tokenProvider.createToken(loginRequest)).thenReturn(authResponse);
     * 
     * mockMvc.perform(post("/auth/login") .content(jsonString(loginRequest))
     * .contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
     * .andDo(print()) .andExpect(status().isOk())
     * .andExpect(jsonPath("$.user",Matchers.equalTo(user))) // TODO match user just
     * created .andExpect(jsonPath("$.jwtToken",Matchers.equalTo("SAMPLE_TOKEN")))
     * .andReturn(); }
     */
}