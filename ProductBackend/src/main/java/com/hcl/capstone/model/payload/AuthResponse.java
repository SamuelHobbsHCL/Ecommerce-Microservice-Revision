package com.hcl.capstone.model.payload;

import com.hcl.capstone.model.User;

public class AuthResponse {
	private User user;
    private String jwtToken;

    public AuthResponse(User user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
