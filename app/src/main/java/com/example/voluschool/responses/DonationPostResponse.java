package com.example.voluschool.responses;

public class DonationPostResponse {
    private boolean success;
    private String message;
    private String status;
    private String filename;

    public DonationPostResponse(boolean success, String message, String status, String filename) {
        this.success = success;
        this.message = message;
        this.status = status;
        this.filename = filename;
    }

}
