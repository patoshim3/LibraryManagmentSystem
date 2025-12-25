package com.example.librarymanagmentsystem;

import com.example.librarymanagmentsystem.dto.AuthorDto;
import com.example.librarymanagmentsystem.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    void getAllTest() {
        List<AuthorDto> authors = authorService.getAll();
        Assertions.assertNotNull(authors);
        Assertions.assertNotEquals(0, authors.size());

        for (AuthorDto dto : authors) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
            Assertions.assertNotNull(dto.getAuthorBiography());
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(authorService.getAll().size());
        Long id = authorService.getAll().get(randomIndex).getId();

        AuthorDto dto = authorService.getAuthorByID(id);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(id, dto.getId());

        AuthorDto notFound = authorService.getAuthorByID(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void addTest() {
        AuthorDto dto = new AuthorDto();
        dto.setName("Test Author");
        dto.setAuthorBiography("Biography text");

        authorService.addAuthor(dto);

        List<AuthorDto> authors = authorService.getAll();
        Assertions.assertTrue(authors.stream().anyMatch(a -> a.getName().equals("Test Author")));
    }

    @Test
    void updateTest() {
        Random random = new Random();
        Long id = authorService.getAll().get(random.nextInt(authorService.getAll().size())).getId();

        AuthorDto dto = new AuthorDto();
        dto.setName("Updated Author");
        dto.setAuthorBiography("Updated biography");

        authorService.updateAuthor(id, dto);

        AuthorDto updated = authorService.getAuthorByID(id);
        Assertions.assertEquals("Updated Author", updated.getName());
        Assertions.assertEquals("Updated biography", updated.getAuthorBiography());
    }

    @Test
    void deleteTest() {
        Random random = new Random();
        Long id = authorService.getAll().get(random.nextInt(authorService.getAll().size())).getId();

        Assertions.assertTrue(authorService.deleteAuthor(id));
        Assertions.assertNull(authorService.getAuthorByID(id));
    }
}