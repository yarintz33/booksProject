package com.yarin.bookService.service;

import com.yarin.bookService.exception.BookNotFoundException;
import com.yarin.bookService.exception.InvalidBookDataException;
import com.yarin.bookService.model.Book;
import com.yarin.bookService.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Book getBookById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new BookNotFoundException(id);
        }
    }
    
    public Book createBook(Book book) {
        String title = book.getTitle();
        String errorMessage = null;
        if (title == null || title.trim().isEmpty()) {
            errorMessage = "Invalid title - cannot be blank";
        }
        if(title.length() > 50) {
            errorMessage = "Invalid title - must be less than 50 characters";
        }
        if (book.getRating() == null || book.getRating() < 1 || book.getRating() > 10) {
            errorMessage = "Invalid rating - must be between 1 and 10";
        }
        if (book.getId() == null || book.getId() < 0 || book.getId() > 20) {
            errorMessage = "Invalid id - must be between 0 and 20";
        }
        if (errorMessage != null) {
            throw new InvalidBookDataException(errorMessage);
        }
        return bookRepository.save(book);
    }
} 