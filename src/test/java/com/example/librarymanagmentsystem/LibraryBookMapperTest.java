package com.example.librarymanagmentsystem;

import com.example.librarymanagmentsystem.Model.Author;
import com.example.librarymanagmentsystem.Model.Book;
import com.example.librarymanagmentsystem.Model.Library;
import com.example.librarymanagmentsystem.Model.LibraryBook;
import com.example.librarymanagmentsystem.dto.LibraryBookDto;
import com.example.librarymanagmentsystem.mapper.LibraryBookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryBookMapperTest {

    @Autowired
    private LibraryBookMapper libraryBookMapper;

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

    private Library createLibrary(Long id, String name, String address) {
        Library library = new Library();
        library.setId(id);
        library.setName(name);
        library.setAddress(address);
        library.setLibraryBooks(new HashSet<>());
        return library;
    }

    private LibraryBook createLibraryBook(Long id, Library library, Book book, Integer quantity, String shelfCode) {
        LibraryBook lb = new LibraryBook();
        lb.setId(id);
        lb.setLibrary(library);
        lb.setBook(book);
        lb.setQuantity(quantity);
        lb.setShelfCode(shelfCode);
        return lb;
    }

    @Test
    void convertEntityToDtoTest() {
        Library library = createLibrary(1L, "Central Library", "Main Street 123");
        Author author = createAuthor(1L, "Author1", "Biography");
        Book book = createBook(1L, "Book Title", "ISBN123", 2020, author);
        LibraryBook libraryBook = createLibraryBook(1L, library, book, 10, "A1");

        LibraryBookDto dto = libraryBookMapper.toDto(libraryBook);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getLibraryId());
        assertEquals("Central Library", dto.getLibraryName());
        assertEquals(1L, dto.getBookId());
        assertEquals("Book Title", dto.getBookTitle());
        assertEquals(10, dto.getQuantity());
        assertEquals("A1", dto.getShelfCode());
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

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(10, entity.getQuantity());
        assertEquals("A1", entity.getShelfCode());
        assertNull(entity.getLibrary());
        assertNull(entity.getBook());
    }

    @Test
    void convertEntityListToDtoListTest() {
        Library library = createLibrary(1L, "Central Library", "Main Street 123");
        Author author = createAuthor(1L, "Author1", "Biography");
        Book book = createBook(1L, "Book Title", "ISBN123", 2020, author);

        List<LibraryBook> libraryBooks = new ArrayList<>();
        libraryBooks.add(createLibraryBook(1L, library, book, 10, "A1"));
        libraryBooks.add(createLibraryBook(2L, library, book, 20, "B2"));
        libraryBooks.add(createLibraryBook(3L, library, book, 30, "C3"));

        List<LibraryBookDto> dtos = libraryBookMapper.toDtoList(libraryBooks);

        assertNotNull(dtos);
        assertEquals(3, dtos.size());

        for (int i = 0; i < libraryBooks.size(); i++) {
            LibraryBook lb = libraryBooks.get(i);
            LibraryBookDto dto = dtos.get(i);

            assertEquals(lb.getId(), dto.getId());
            assertEquals(lb.getLibrary().getId(), dto.getLibraryId());
            assertEquals(lb.getLibrary().getName(), dto.getLibraryName());
            assertEquals(lb.getBook().getId(), dto.getBookId());
            assertEquals(lb.getBook().getTitle(), dto.getBookTitle());
            assertEquals(lb.getQuantity(), dto.getQuantity());
            assertEquals(lb.getShelfCode(), dto.getShelfCode());
        }
    }

    @Test
    void updateEntityFromDtoTest() {
        Library library = createLibrary(1L, "Central Library", "Main Street 123");
        Author author = createAuthor(1L, "Author1", "Biography");
        Book book = createBook(1L, "Book Title", "ISBN123", 2020, author);

        LibraryBook entity = createLibraryBook(1L, library, book, 10, "A1");

        LibraryBookDto dto = LibraryBookDto.builder()
                .quantity(50)
                .shelfCode(null)
                .build();

        libraryBookMapper.updateEntityFromDto(dto, entity);

        assertEquals(50, entity.getQuantity());
        assertEquals("A1", entity.getShelfCode());
        assertEquals(library, entity.getLibrary());
        assertEquals(book, entity.getBook());
    }
}