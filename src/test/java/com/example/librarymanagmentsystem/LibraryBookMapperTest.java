package com.example.librarymanagmentsystem;

import com.example.librarymanagmentsystem.Model.Author;
import com.example.librarymanagmentsystem.Model.Book;
import com.example.librarymanagmentsystem.Model.Library;
import com.example.librarymanagmentsystem.Model.LibraryBook;
import com.example.librarymanagmentsystem.dto.LibraryBookDto;
import com.example.librarymanagmentsystem.mapper.LibraryBookMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LibraryBookMapperTest {

    @Autowired
    private LibraryBookMapper libraryBookMapper;

    @Test
    void convertEntityToDtoTest() {
        Library library = new Library(1L, "Central Library", "Main Street 123", null);
        Author author = new Author(1L, "Author1", "Biography");
        Book book = new Book(1L, "Book Title", "ISBN123", 2020, author);
        LibraryBook libraryBook = new LibraryBook(1L, library, book, 10, "A1");
        LibraryBookDto dto = libraryBookMapper.toDto(libraryBook);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(libraryBook.getId(), dto.getId());
        Assertions.assertEquals(libraryBook.getLibrary().getId(), dto.getLibraryId());
        Assertions.assertEquals(libraryBook.getLibrary().getName(), dto.getLibraryName());
        Assertions.assertEquals(libraryBook.getBook().getId(), dto.getBookId());
        Assertions.assertEquals(libraryBook.getBook().getTitle(), dto.getBookTitle());
        Assertions.assertEquals(libraryBook.getQuantity(), dto.getQuantity());
        Assertions.assertEquals(libraryBook.getShelfCode(), dto.getShelfCode());
    }

    @Test
    void convertDtoToEntityTest() {
        LibraryBookDto dto = LibraryBookDto.builder()
                .id(1L)
                .libraryId(1L)
                .libraryName("Central Library")
                .bookId(1L)
                .bookTitle("Book Title")
                .quantity(10)
                .shelfCode("A1")
                .build();

        LibraryBook entity = libraryBookMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getQuantity(), entity.getQuantity());
        Assertions.assertEquals(dto.getShelfCode(), entity.getShelfCode());

        Assertions.assertNull(entity.getLibrary());
        Assertions.assertNull(entity.getBook());
    }

    @Test
    void convertEntityListToDtoListTest() {
        Library library = new Library(1L, "Central Library", "Main Street 123", null);
        Author author = new Author(1L, "Author1", "Biography");
        Book book = new Book(1L, "Book Title", "ISBN123", 2020, author);

        List<LibraryBook> libraryBooks = new ArrayList<>();
        libraryBooks.add(new LibraryBook(1L, library, book, 10, "A1"));
        libraryBooks.add(new LibraryBook(2L, library, book, 20, "B2"));
        libraryBooks.add(new LibraryBook(3L, library, book, 30, "C3"));
        List<LibraryBookDto> dtos = libraryBookMapper.toDtoList(libraryBooks);

        Assertions.assertNotNull(dtos);
        Assertions.assertEquals(libraryBooks.size(), dtos.size());

        for (int i = 0; i < libraryBooks.size(); i++) {
            LibraryBook lb = libraryBooks.get(i);
            LibraryBookDto dto = dtos.get(i);

            Assertions.assertEquals(lb.getId(), dto.getId());
            Assertions.assertEquals(lb.getLibrary().getId(), dto.getLibraryId());
            Assertions.assertEquals(lb.getLibrary().getName(), dto.getLibraryName());
            Assertions.assertEquals(lb.getBook().getId(), dto.getBookId());
            Assertions.assertEquals(lb.getBook().getTitle(), dto.getBookTitle());
            Assertions.assertEquals(lb.getQuantity(), dto.getQuantity());
            Assertions.assertEquals(lb.getShelfCode(), dto.getShelfCode());
        }
    }

    @Test
    void updateEntityFromDtoTest() {
        Library library = new Library(1L, "Central Library", "Main Street 123", null);
        Author author = new Author(1L, "Author1", "Biography");
        Book book = new Book(1L, "Book Title", "ISBN123", 2020, author);

        LibraryBook entity = new LibraryBook(1L, library, book, 10, "A1");

        LibraryBookDto dto = LibraryBookDto.builder()
                .quantity(50)
                .shelfCode(null)
                .build();

        libraryBookMapper.updateEntityFromDto(dto, entity);

        Assertions.assertEquals(50, entity.getQuantity());
        Assertions.assertEquals("A1", entity.getShelfCode());
        Assertions.assertEquals(library, entity.getLibrary());
        Assertions.assertEquals(book, entity.getBook());
    }
}