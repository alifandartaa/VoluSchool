package com.example.voluschool.responses;

public class RegisterResponse {
    boolean success;
    String message;

    public RegisterResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
