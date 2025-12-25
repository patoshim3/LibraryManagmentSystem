package com.example.librarymanagmentsystem.service;

import com.example.librarymanagmentsystem.dto.LibraryDto;

import java.util.List;

public interface LibraryService {
    List<LibraryDto> getAll();
    LibraryDto getById(Long id);
    void addLibrary(LibraryDto dto);
    void updateLibrary(Long id, LibraryDto dto);
    boolean deleteLibrary(Long id);

}
