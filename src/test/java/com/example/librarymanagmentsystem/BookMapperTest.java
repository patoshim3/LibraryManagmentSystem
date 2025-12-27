package com.example.librarymanagmentsystem;

import com.example.librarymanagmentsystem.dto.BookDto;
import com.example.librarymanagmentsystem.mapper.BookMapper;
import com.example.librarymanagmentsystem.Model.Author;
import com.example.librarymanagmentsystem.Model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    private Author createAuthor(Long id, String name, String biography) {
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        author.setAuthorBiography(biography);
        author.setBooks(new ArrayList<>());
        return author;
    }

    private Book createBook(Long id, String title, String isbn, Integer year, Author author) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPublicationYear(year);
        book.setAuthor(author);
        book.setLibraryBooks(new HashSet<>());
        return book;
    }

    @Test
    void convertEntityToDtoTest() {
        Author author = createAuthor(1L, "Лев Толстой", "Великий писатель");
        Book entity = createBook(1L, "Война и мир", "978-5-17-123456-7", 1869, author);

        BookDto dto = bookMapper.toDto(entity);

        assertEquals(1L, dto.getId());
        assertEquals("Война и мир", dto.getTitle());
        assertEquals("978-5-17-123456-7", dto.getIsbn());
        assertEquals(1869, dto.getPublicationYear());
        assertEquals(1L, dto.getAuthorId());
    }

    @Test
    void convertDtoToEntityTest() {
        BookDto dto = BookDto.builder()
                .id(1L)
                .title("1984")
                .isbn("978-0451524935")
                .publicationYear(1949)
                .authorId(1L)
                .build();

        Book entity = bookMapper.toEntity(dto);

        assertEquals(1L, entity.getId());
        assertEquals("1984", entity.getTitle());
        assertEquals("978-0451524935", entity.getIsbn());
        assertEquals(1949, entity.getPublicationYear());
        assertNull(entity.getAuthor());
        assertNotNull(entity.getLibraryBooks());
        assertTrue(entity.getLibraryBooks().isEmpty());
    }

    @Test
    void convertEntityListToDtoList() {
        Author author1 = createAuthor(1L, "Автор1", "Био1");
        Author author2 = createAuthor(2L, "Автор2", "Био2");

        List<Book> entities = List.of(
                createBook(1L, "Книга1", "isbn1", 2000, author1),
                createBook(2L, "Книга2", "isbn2", 2001, author2),
                createBook(3L, "Книга3", "isbn3", 2002, null)
        );

        List<BookDto> dtos = bookMapper.toDtoList(entities);

        assertEquals(3, dtos.size());

        for (int i = 0; i < entities.size(); i++) {
            Book entity = entities.get(i);
            BookDto dto = dtos.get(i);

            assertEquals(entity.getId(), dto.getId());
            assertEquals(entity.getTitle(), dto.getTitle());
            assertEquals(entity.getIsbn(), dto.getIsbn());
            assertEquals(entity.getPublicationYear(), dto.getPublicationYear());

            if (entity.getAuthor() != null) {
                assertEquals(entity.getAuthor().getId(), dto.getAuthorId());
            } else {
                assertNull(dto.getAuthorId());
            }
        }
    }
}