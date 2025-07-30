package com.yarin.recommendedbookservice.model;

public class Book {
    private int id;
    private String title;
    private int rating;

    // Default constructor
    public Book() {}

    // Constructor with all fields
    public Book(int id, String title, int rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // @Override
    // public String toString() {
    //     return "Book{" +
    //             "id=" + id +
    //             ", title='" + title + '\'' +
    //             ", rating=" + rating +
    //             '}';
    // }
} 