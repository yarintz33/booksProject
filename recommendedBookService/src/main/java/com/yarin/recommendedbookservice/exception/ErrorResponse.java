package com.yarin.recommendedbookservice.exception;

public class ErrorResponse {
    private int httpCode;
    private String message;

    // Default constructor
    public ErrorResponse() {}

    // Constructor with all fields
    public ErrorResponse(int httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

    // Getters and Setters
    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
} 