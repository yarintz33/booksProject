package com.yarin.bookService.exception;

public class InvalidBookDataException extends RuntimeException {
    
    public InvalidBookDataException(String message) {
        super(message);
    }
} 