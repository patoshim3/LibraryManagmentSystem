package com.example.librarymanagmentsystem;

import com.example.librarymanagmentsystem.dto.BookDto;
import com.example.librarymanagmentsystem.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

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
            Assertions.assertNotNull(dto.getIsbn());
            Assertions.assertNotNull(dto.getPublicationYear());
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(bookService.getAll().size());
        Long id = bookService.getAll().get(randomIndex).getId();

        BookDto dto = bookService.getById(id);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(id, dto.getId());

        BookDto notFound = bookService.getById(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void addTest() {
        BookDto dto = new BookDto();
        dto.setTitle("Test Book");
        dto.setIsbn("ISBN-TEST");
        dto.setPublicationYear(2025);
        bookService.addBook(dto);

        List<BookDto> books = bookService.getAll();
        Assertions.assertTrue(books.stream().anyMatch(b -> b.getTitle().equals("Test Book")));
    }

    @Test
    void updateTest() {
        Random random = new Random();
        Long id = bookService.getAll().get(random.nextInt(bookService.getAll().size())).getId();

        BookDto dto = new BookDto();
        dto.setTitle("Updated Book");
        dto.setIsbn("Updated ISBN");
        dto.setPublicationYear(2030);
        bookService.updateBook(id, dto);

        BookDto updated = bookService.getById(id);
        Assertions.assertEquals("Updated Book", updated.getTitle());
        Assertions.assertEquals("Updated ISBN", updated.getIsbn());
        Assertions.assertEquals(2030, updated.getPublicationYear());
    }

    @Test
    void deleteTest() {
        Random random = new Random();
        Long id = bookService.getAll().get(random.nextInt(bookService.getAll().size())).getId();

        Assertions.assertTrue(bookService.deleteBook(id));
        Assertions.assertNull(bookService.getById(id));
    }
}
