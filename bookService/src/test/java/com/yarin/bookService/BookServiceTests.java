package com.yarin.bookService;

import com.yarin.bookService.exception.BookNotFoundException;
import com.yarin.bookService.model.Book;
import com.yarin.bookService.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BookServiceTests {

    @Autowired
    private BookService bookService;

    // @Autowired
    // private BookRepository bookRepository;

    // @BeforeEach
    // void setUp() {
    //     bookRepository.deleteAll();
    // }

    @Test
    void testCreateBook() {
        Book book = new Book(1, "Test Book", 8);

        Book savedBook = bookService.createBook(book);

        assertNotNull(savedBook);
        assertEquals(1, savedBook.getId());
        assertEquals("Test Book", savedBook.getTitle());
        assertEquals(8, savedBook.getRating());
    }

    @Test
    void testGetAllBooks() {
        Book book1 = new Book(1, "Book 1", 7);
        Book book2 = new Book(2, "Book 2", 9);
        bookService.createBook(book1);
        bookService.createBook(book2);

        List<Book> books = bookService.getAllBooks();

        assertEquals(2, books.size());
    }

    @Test
    void testGetBookById() {
        Book book = new Book(1, "Test Book", 8);
        bookService.createBook(book);

        Book foundBook = bookService.getBookById(1);

        assertEquals("Test Book", foundBook.getTitle());
    }

    @Test
    void testGetBookByIdNotFound() {
        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(999));
    }
} 