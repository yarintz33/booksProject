package com.yarin.bookService.service;

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
    
    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }
    
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
} 