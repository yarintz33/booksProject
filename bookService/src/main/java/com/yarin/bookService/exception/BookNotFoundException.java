package com.yarin.bookService.exception;

public class BookNotFoundException extends RuntimeException {
    
    public BookNotFoundException(String message) {
        super(message);
    }
    
    public BookNotFoundException(int id) {
        super("Book not found with id: " + id);
    }
} 