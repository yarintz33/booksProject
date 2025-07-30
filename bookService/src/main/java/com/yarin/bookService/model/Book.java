package com.yarin.bookService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

@Document(collection = "books")
public class Book {
    
    @Id
    @Min(value = 0, message = "ID must be between 0 and 20")
    @Max(value = 20, message = "ID must be between 0 and 20")
    private Integer id;
    
    @NotBlank(message = "Title is required")
    @Size(max = 50, message = "Title must be up to 50 characters")
    private String title;
    
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be between 1 and 10")
    @Max(value = 10, message = "Rating must be between 1 and 10")
    private Integer rating;
    
    // Default constructor
    public Book() {}
    
    // Constructor with all fields
    public Book(Integer id, String title, Integer rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getRating() {
        return rating;
    }
    
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    // public String toString() {
    //     return "Book{" +
    //             "id=" + id +
    //             ", title='" + title + '\'' +
    //             ", rating=" + rating +
    //             '}';
    // }
} 