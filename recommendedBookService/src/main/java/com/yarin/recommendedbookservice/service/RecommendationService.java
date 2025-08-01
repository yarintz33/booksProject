package com.yarin.recommendedbookservice.service;

import com.yarin.recommendedbookservice.exception.BookServiceUnavailableException;
import com.yarin.recommendedbookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final WebClient webClient;
    private final String bookServiceUrl;

    @Autowired
    public RecommendationService(WebClient webClient, 
                               @Value("${book.service.url:http://localhost:8080}") String bookServiceUrl) {
        this.webClient = webClient;
        this.bookServiceUrl = bookServiceUrl;
    }

    public List<Book> getRecommendedBooks() {
        try {
            // Call the book service to get all books using WebClient
            List<Book> allBooks = webClient.get()
                    .uri(bookServiceUrl + "/api/books")
                    .retrieve()
                    .bodyToMono(new org.springframework.core.ParameterizedTypeReference<List<Book>>() {})
                    .block();
            
            // Sort books by rating in descending order (highest rating first)
            return allBooks.stream()
                    .sorted(Comparator.comparing(Book::getRating, Comparator.nullsLast(Comparator.reverseOrder())))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BookServiceUnavailableException("Unable to retrieve books from Book Service", e);
        }
    }
} 