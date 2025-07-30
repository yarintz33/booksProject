package com.yarin.bookService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

@Document(collection = "books")
public class Book {
    
    @Id
    @NotNull(message = "ID is required")
    private Integer id;
   
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotNull(message = "Rating is required")
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