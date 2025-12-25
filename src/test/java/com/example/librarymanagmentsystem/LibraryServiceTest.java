package com.example.librarymanagmentsystem;

import com.example.librarymanagmentsystem.dto.LibraryDto;
import com.example.librarymanagmentsystem.service.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class LibraryServiceTest {

    @Autowired
    private LibraryService libraryService;

    @Test
    void getAllTest() {
        List<LibraryDto> libraries = libraryService.getAll();
        Assertions.assertNotNull(libraries);
        Assertions.assertNotEquals(0, libraries.size());

        for (LibraryDto dto : libraries) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
            Assertions.assertNotNull(dto.getAddress());
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        Long id = libraryService.getAll().get(random.nextInt(libraryService.getAll().size())).getId();

        LibraryDto dto = libraryService.getById(id);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(id, dto.getId());

        LibraryDto notFound = libraryService.getById(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void addTest() {
        LibraryDto dto = new LibraryDto();
        dto.setName("Test Library");
        dto.setAddress("Test Address");

        libraryService.addLibrary(dto);

        List<LibraryDto> libraries = libraryService.getAll();
        Assertions.assertTrue(libraries.stream().anyMatch(l -> l.getName().equals("Test Library")));
    }

    @Test
    void updateTest() {
        Random random = new Random();
        Long id = libraryService.getAll().get(random.nextInt(libraryService.getAll().size())).getId();

        LibraryDto dto = new LibraryDto();
        dto.setName("Updated Library");
        dto.setAddress("Updated Address");

        libraryService.updateLibrary(id, dto);

        LibraryDto updated = libraryService.getById(id);
        Assertions.assertEquals("Updated Library", updated.getName());
        Assertions.assertEquals("Updated Address", updated.getAddress());
    }

    @Test
    void deleteTest() {
        Random random = new Random();
        Long id = libraryService.getAll().get(random.nextInt(libraryService.getAll().size())).getId();

        Assertions.assertTrue(libraryService.deleteLibrary(id));
        Assertions.assertNull(libraryService.getById(id));
    }
}