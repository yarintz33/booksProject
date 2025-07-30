package com.yarin.recommendedbookservice.exception;

public class ErrorResponse {
    private int httpCode;
    private String message;

    public ErrorResponse() {}

    public ErrorResponse(int httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

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