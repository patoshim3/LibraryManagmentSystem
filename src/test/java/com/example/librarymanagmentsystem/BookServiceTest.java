package com.example.librarymanagmentsystem;

import com.example.librarymanagmentsystem.dto.BookDto;
import com.example.librarymanagmentsystem.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void getAllTest() {
        List<BookDto> books = bookService.getAll();
        Assertions.assertNotNull(books);
        Assertions.assertNotEquals(0, books.size());

        for (BookDto dto : books) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getTitle());
        }
    }

    @Test
    void getByIdTest() {
        List<BookDto> allBooks = bookService.getAll();
        int randomIndex = (int) (Math.random() * allBooks.size());
        Long id = allBooks.get(randomIndex).getId();

        BookDto dto = bookService.getBookById(id);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(id, dto.getId());

        BookDto notFound = bookService.getBookById(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void addTest() {
        BookDto dto = new BookDto();
        dto.setTitle("Test Book");
        dto.setIsbn("ISBN-TEST");
        dto.setPublicationYear(2025);

        // Добавляем существующего автора из БД
        List<BookDto> allBooks = bookService.getAll();
        Long existingAuthorId = allBooks.get(0).getAuthorId();

        dto.setAuthorId(existingAuthorId);

        bookService.addBook(dto);

        List<BookDto> books = bookService.getAll();
        Assertions.assertTrue(books.stream().anyMatch(b -> b.getTitle().equals("Test Book")));
    }

    @Test
    void updateTest() {
        List<BookDto> allBooks = bookService.getAll();
        Assertions.assertFalse(allBooks.isEmpty(), "Нет книг для тестирования обновления");

        int randomIndex = (int) (Math.random() * allBooks.size());
        Long id = allBooks.get(randomIndex).getId();
        Long existingAuthorId = allBooks.get(randomIndex).getAuthorId();

        BookDto dto = new BookDto();
        dto.setTitle("Updated Book");
        dto.setIsbn("Updated ISBN");
        dto.setPublicationYear(2030);
        dto.setAuthorId(existingAuthorId);

        bookService.updateBook(id, dto);

        BookDto updated = bookService.getBookById(id);
        Assertions.assertNotNull(updated);
        Assertions.assertEquals("Updated Book", updated.getTitle());
        Assertions.assertEquals("Updated ISBN", updated.getIsbn());
        Assertions.assertEquals(Integer.valueOf(2030), updated.getPublicationYear());
        Assertions.assertEquals(existingAuthorId, updated.getAuthorId());
    }

    @Test
    void deleteTest() {
        List<BookDto> allBooks = bookService.getAll();
        int randomIndex = (int) (Math.random() * allBooks.size());
        Long id = allBooks.get(randomIndex).getId();

        Assertions.assertTrue(bookService.deleteBook(id));
        Assertions.assertNull(bookService.getBookById(id));
    }
}