package com.yarin.recommendedbookservice.service;

import com.yarin.recommendedbookservice.client.BookServiceClient;
import com.yarin.recommendedbookservice.exception.BookServiceUnavailableException;
import com.yarin.recommendedbookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final BookServiceClient bookServiceClient;

    @Autowired
    public RecommendationService(BookServiceClient bookServiceClient) {
        this.bookServiceClient = bookServiceClient;
    }

    public List<Book> getRecommendedBooks() {
        try {
            // Call the book service to get all books
            List<Book> allBooks = bookServiceClient.getAllBooks();
            
            // Sort books by rating in descending order (highest rating first)
            return allBooks.stream()
                    .sorted(Comparator.comparing(Book::getRating, Comparator.nullsLast(Comparator.reverseOrder())))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BookServiceUnavailableException("Unable to retrieve books from Book Service", e);
        }
    }
} 