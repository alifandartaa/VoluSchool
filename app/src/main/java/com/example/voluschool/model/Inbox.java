package com.example.voluschool.model;

import java.util.Date;

public class Inbox {
    private String message;

    public Inbox(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
