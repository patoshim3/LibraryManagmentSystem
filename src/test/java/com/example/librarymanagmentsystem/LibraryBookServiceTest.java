package com.example.librarymanagmentsystem;

import com.example.librarymanagmentsystem.dto.LibraryBookDto;
import com.example.librarymanagmentsystem.service.LibraryBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class LibraryBookServiceTest {

    @Autowired
    private LibraryBookService libraryBookService;

    @Test
    void getAllTest() {
        List<LibraryBookDto> libraryBooks = libraryBookService.getAll();
        Assertions.assertNotNull(libraryBooks);
        Assertions.assertNotEquals(0, libraryBooks.size());

        for (LibraryBookDto dto : libraryBooks) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getLibraryId());
            Assertions.assertNotNull(dto.getLibraryName());
            Assertions.assertNotNull(dto.getBookId());
            Assertions.assertNotNull(dto.getBookTitle());
            Assertions.assertNotNull(dto.getQuantity());
            Assertions.assertNotNull(dto.getShelfCode());
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(libraryBookService.getAll().size());
        Long id = libraryBookService.getAll().get(randomIndex).getId();

        LibraryBookDto dto = libraryBookService.getById(id);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(id, dto.getId());

        LibraryBookDto notFound = libraryBookService.getById(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void addTest() {
        LibraryBookDto dto = LibraryBookDto.builder()
                .libraryId(1L)
                .libraryName("Central Library")
                .bookId(1L)
                .bookTitle("Test Book")
                .quantity(5)
                .shelfCode("Z9")
                .build();

        libraryBookService.addLibraryBook(dto);

        List<LibraryBookDto> libraryBooks = libraryBookService.getAll();
        Assertions.assertTrue(libraryBooks.stream().anyMatch(lb -> "Z9".equals(lb.getShelfCode())));
    }

    @Test
    void updateTest() {
        Random random = new Random();
        Long id = libraryBookService.getAll().get(random.nextInt(libraryBookService.getAll().size())).getId();

        LibraryBookDto dto = LibraryBookDto.builder()
                .quantity(99)
                .shelfCode("X1")
                .build();

        libraryBookService.updateLibraryBook(id, dto);

        LibraryBookDto updated = libraryBookService.getById(id);
        Assertions.assertEquals(99, updated.getQuantity());
        Assertions.assertEquals("X1", updated.getShelfCode());
    }

    @Test
    void deleteTest() {
        Random random = new Random();
        Long id = libraryBookService.getAll().get(random.nextInt(libraryBookService.getAll().size())).getId();

        Assertions.assertTrue(libraryBookService.deleteLibraryBook(id));
        Assertions.assertNull(libraryBookService.getById(id));
    }

    @Test
    void getBooksByLibraryTest() {
        Long libraryId = libraryBookService.getAll().get(0).getLibraryId();
        List<LibraryBookDto> books = libraryBookService.getBooksByLibrary(libraryId);

        Assertions.assertNotNull(books);
        Assertions.assertTrue(books.stream().allMatch(b -> b.getLibraryId().equals(libraryId)));
    }

    @Test
    void getLibrariesByBookTest() {
        Long bookId = libraryBookService.getAll().get(0).getBookId();
        List<LibraryBookDto> libraries = libraryBookService.getLibrariesByBook(bookId);

        Assertions.assertNotNull(libraries);
        Assertions.assertTrue(libraries.stream().allMatch(lb -> lb.getBookId().equals(bookId)));
    }
}