package com.example.touristguide.dto;

public class UserJWTResponse {
    private String token;


    public UserJWTResponse() {
    }

    public UserJWTResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
