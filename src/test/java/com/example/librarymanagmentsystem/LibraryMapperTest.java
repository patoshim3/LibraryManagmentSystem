package com.example.librarymanagmentsystem;

import com.example.librarymanagmentsystem.Model.Library;
import com.example.librarymanagmentsystem.dto.LibraryDto;
import com.example.librarymanagmentsystem.mapper.LibraryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LibraryMapperTest {

    @Autowired
    private LibraryMapper libraryMapper;

    @Test
    void convertEntityToDtoTest() {
        Library entityLibrary = new Library(1L, "Central Library", "Main Street 123", null);
        LibraryDto dtoLibrary = libraryMapper.toDto(entityLibrary);

        Assertions.assertNotNull(dtoLibrary);
        Assertions.assertNotNull(dtoLibrary.getId());
        Assertions.assertNotNull(dtoLibrary.getName());
        Assertions.assertNotNull(dtoLibrary.getAddress());

        Assertions.assertEquals(entityLibrary.getId(), dtoLibrary.getId());
        Assertions.assertEquals(entityLibrary.getName(), dtoLibrary.getName());
        Assertions.assertEquals(entityLibrary.getAddress(), dtoLibrary.getAddress());
    }

    @Test
    void convertDtoToEntityTest() {
        LibraryDto libraryDto = LibraryDto.builder()
                .id(1L)
                .name("Central Library")
                .address("Main Street 123")
                .build();

        Library library = libraryMapper.toEntity(libraryDto);

        Assertions.assertNotNull(library);
        Assertions.assertNotNull(library.getId());
        Assertions.assertNotNull(library.getName());
        Assertions.assertNotNull(library.getAddress());

        Assertions.assertEquals(libraryDto.getId(), library.getId());
        Assertions.assertEquals(libraryDto.getName(), library.getName());
        Assertions.assertEquals(libraryDto.getAddress(), library.getAddress());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Library> libraryList = new ArrayList<>();
        libraryList.add(new Library(1L, "Library1", "Address1", null));
        libraryList.add(new Library(2L, "Library2", "Address2", null));
        libraryList.add(new Library(3L, "Library3", "Address3", null));

        List<LibraryDto> libraryDtoList = libraryMapper.toDtoList(libraryList);

        Assertions.assertNotNull(libraryDtoList);
        Assertions.assertEquals(libraryList.size(), libraryDtoList.size());

        for (int i = 0; i < libraryList.size(); i++) {
            Library library = libraryList.get(i);
            LibraryDto libraryDto = libraryDtoList.get(i);

            Assertions.assertEquals(library.getId(), libraryDto.getId());
            Assertions.assertEquals(library.getName(), libraryDto.getName());
            Assertions.assertEquals(library.getAddress(), libraryDto.getAddress());
        }
    }

    @Test
    void updateEntityFromDtoTest() {
        Library library = new Library(1L, "Old Name", "Old Address", null);
        LibraryDto dto = LibraryDto.builder()
                .name("New Name")
                .address(null) // address остаётся прежним
                .build();

        libraryMapper.updateEntityFromDto(dto, library);

        Assertions.assertEquals("New Name", library.getName());
        Assertions.assertEquals("Old Address", library.getAddress()); // не перезаписалось null
    }
}