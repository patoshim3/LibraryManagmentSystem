package com.example.librarymanagmentsystem;

import com.example.librarymanagmentsystem.Model.Library;
import com.example.librarymanagmentsystem.dto.LibraryDto;
import com.example.librarymanagmentsystem.mapper.LibraryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryMapperTest {

    @Autowired
    private LibraryMapper libraryMapper;

    private Library createLibrary(Long id, String name, String address) {
        Library library = new Library();
        library.setId(id);
        library.setName(name);
        library.setAddress(address);
        library.setLibraryBooks(new HashSet<>());
        return library;
    }

    @Test
    void convertEntityToDtoTest() {
        Library entity = createLibrary(1L, "Central Library", "Main Street 123");

        LibraryDto dto = libraryMapper.toDto(entity);

        assertEquals(1L, dto.getId());
        assertEquals("Central Library", dto.getName());
        assertEquals("Main Street 123", dto.getAddress());
    }

    @Test
    void convertDtoToEntityTest() {
        LibraryDto dto = LibraryDto.builder()
                .id(1L)
                .name("Central Library")
                .address("Main Street 123")
                .build();

        Library entity = libraryMapper.toEntity(dto);

        assertEquals(1L, entity.getId());
        assertEquals("Central Library", entity.getName());
        assertEquals("Main Street 123", entity.getAddress());
        assertNotNull(entity.getLibraryBooks());
        assertTrue(entity.getLibraryBooks().isEmpty());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Library> entities = List.of(
                createLibrary(1L, "Library1", "Address1"),
                createLibrary(2L, "Library2", "Address2"),
                createLibrary(3L, "Library3", "Address3")
        );

        List<LibraryDto> dtos = libraryMapper.toDtoList(entities);

        assertEquals(3, dtos.size());

        for (int i = 0; i < entities.size(); i++) {
            Library entity = entities.get(i);
            LibraryDto dto = dtos.get(i);

            assertEquals(entity.getId(), dto.getId());
            assertEquals(entity.getName(), dto.getName());
            assertEquals(entity.getAddress(), dto.getAddress());
        }
    }

    @Test
    void updateEntityFromDtoTest() {
        Library entity = createLibrary(1L, "Old Name", "Old Address");

        LibraryDto dto = LibraryDto.builder()
                .name("New Name")
                .address(null)
                .build();

        libraryMapper.updateEntityFromDto(dto, entity);

        assertEquals("New Name", entity.getName());
        assertEquals("Old Address", entity.getAddress());
    }
}