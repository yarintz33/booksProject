package com.yarin.recommendedbookservice.service;

import com.yarin.recommendedbookservice.exception.BookServiceUnavailableException;
import com.yarin.recommendedbookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class RecommendationService {

    private final WebClient webClient;
    private final String bookServiceUrl;

    @Autowired
    public RecommendationService(WebClient webClient, 
                               @Value("${book.service.url}") String bookServiceUrl) {
        this.webClient = webClient;
        this.bookServiceUrl = bookServiceUrl;
    }

    public List<Book> getRecommendedBooks() {
        try {
            List<Book> allBooks = webClient.get()
                    .uri(bookServiceUrl + "/api/books")
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Book>>() {})
                    .block();
            
            Collections.sort(allBooks, Comparator.comparing(Book::getRating, Comparator.nullsLast(Comparator.reverseOrder())));
            return allBooks;
        } catch (Exception e) {
            throw new BookServiceUnavailableException("Unable to retrieve books from Book Service", e);
        }
    }
} 