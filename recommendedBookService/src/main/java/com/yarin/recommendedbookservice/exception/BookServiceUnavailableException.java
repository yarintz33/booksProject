package com.yarin.recommendedbookservice.exception;

public class BookServiceUnavailableException extends RuntimeException {
    
    public BookServiceUnavailableException(String message) {
        super(message);
    }
    
    public BookServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
} 