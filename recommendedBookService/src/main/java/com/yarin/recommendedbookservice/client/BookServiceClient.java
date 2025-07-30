package com.yarin.recommendedbookservice.client;

import com.yarin.recommendedbookservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "book-service", url = "${book.service.url:http://localhost:8080}")
public interface BookServiceClient {

    @GetMapping("/api/books")
    List<Book> getAllBooks();
} 