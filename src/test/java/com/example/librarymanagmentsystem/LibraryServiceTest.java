package com.example.librarymanagmentsystem;

import com.example.librarymanagmentsystem.dto.LibraryDto;
import com.example.librarymanagmentsystem.service.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        List<LibraryDto> all = libraryService.getAll();
        int randomIndex = (int) (Math.random() * all.size());
        Long id = all.get(randomIndex).getId();

        LibraryDto dto = libraryService.getById(id);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(id, dto.getId());

        LibraryDto notFound = libraryService.getById(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void addAndDeleteTest() {
        String uniqueName = "Test Library " + System.currentTimeMillis();

        LibraryDto dto = new LibraryDto();
        dto.setName(uniqueName);
        dto.setAddress("Test Address");

        libraryService.addLibrary(dto);

        List<LibraryDto> allAfterAdd = libraryService.getAll();
        LibraryDto created = allAfterAdd.stream()
                .filter(l -> uniqueName.equals(l.getName()))
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(created);
        Long createdId = created.getId();

        Assertions.assertTrue(libraryService.deleteLibrary(createdId));
        Assertions.assertNull(libraryService.getById(createdId));
    }

    @Test
    void updateTest() {
        List<LibraryDto> all = libraryService.getAll();
        int randomIndex = (int) (Math.random() * all.size());
        Long id = all.get(randomIndex).getId();

        LibraryDto dto = new LibraryDto();
        dto.setName("Updated Library");
        dto.setAddress("Updated Address");

        libraryService.updateLibrary(id, dto);

        LibraryDto updated = libraryService.getById(id);
        Assertions.assertEquals("Updated Library", updated.getName());
        Assertions.assertEquals("Updated Address", updated.getAddress());
    }
}