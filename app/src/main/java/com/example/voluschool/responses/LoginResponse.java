package com.example.voluschool.responses;

public class LoginResponse {
    private boolean success;
    private String token;

    public LoginResponse(boolean success, String token) {
        this.success = success;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
