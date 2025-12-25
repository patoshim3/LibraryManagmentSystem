package com.example.librarymanagmentsystem;

import com.example.librarymanagmentsystem.Model.Author;
import com.example.librarymanagmentsystem.Model.Book;
import com.example.librarymanagmentsystem.dto.BookDto;
import com.example.librarymanagmentsystem.mapper.BookMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void convertEntityToDtoTest() {
        Author author = new Author(1L, "Author1", "Biography of Author1");
        Book book = new Book(1L, "Book Title", "ISBN123", 2020, author);
        BookDto dto = bookMapper.toDto(book);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(book.getId(), dto.getId());
        Assertions.assertEquals(book.getTitle(), dto.getTitle());
        Assertions.assertEquals(book.getIsbn(), dto.getIsbn());
        Assertions.assertEquals(book.getPublicationYear(), dto.getPublicationYear());
        Assertions.assertEquals(book.getAuthor().getId(), dto.getAuthorId());
        Assertions.assertEquals(book.getAuthor().getName(), dto.getAuthorName());
    }

    @Test
    void convertDtoToEntityTest() {
        BookDto dto = new BookDto(1L, "Book Title", "ISBN123", 2020, 1L, "Author1");
        Book book = bookMapper.toEntity(dto);

        Assertions.assertNotNull(book);
        Assertions.assertEquals(dto.getId(), book.getId());
        Assertions.assertEquals(dto.getTitle(), book.getTitle());
        Assertions.assertEquals(dto.getIsbn(), book.getIsbn());
        Assertions.assertEquals(dto.getPublicationYear(), book.getPublicationYear());

        Assertions.assertNull(book.getAuthor());
    }

    @Test
    void convertEntityListToDtoListTest() {
        Author author = new Author(1L, "Author1", "Biography of Author1");
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Book1", "ISBN1", 2000, author));
        books.add(new Book(2L, "Book2", "ISBN2", 2005, author));
        books.add(new Book(3L, "Book3", "ISBN3", 2010, author));
        List<BookDto> dtos = bookMapper.toDtoList(books);

        Assertions.assertNotNull(dtos);
        Assertions.assertEquals(books.size(), dtos.size());

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            BookDto dto = dtos.get(i);

            Assertions.assertEquals(book.getId(), dto.getId());
            Assertions.assertEquals(book.getTitle(), dto.getTitle());
            Assertions.assertEquals(book.getIsbn(), dto.getIsbn());
            Assertions.assertEquals(book.getPublicationYear(), dto.getPublicationYear());
            Assertions.assertEquals(book.getAuthor().getId(), dto.getAuthorId());
            Assertions.assertEquals(book.getAuthor().getName(), dto.getAuthorName());
        }
    }

    @Test
    void updateEntityFromDtoTest() {
        Author author = new Author(1L, "Author1", "Biography of Author1");
        Book book = new Book(1L, "Old Title", "OldISBN", 1990, author);

        BookDto dto = new BookDto(null, "New Title", null, 2000, null, null);
        bookMapper.updateEntityFromDto(dto, book);

        Assertions.assertEquals("New Title", book.getTitle());
        Assertions.assertEquals("OldISBN", book.getIsbn());
        Assertions.assertEquals(2000, book.getPublicationYear());
        Assertions.assertEquals(author, book.getAuthor());
    }
}