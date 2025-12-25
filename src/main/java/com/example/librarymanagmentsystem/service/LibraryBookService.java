package com.example.librarymanagmentsystem.service;

import com.example.librarymanagmentsystem.dto.LibraryBookDto;
import java.util.List;

public interface LibraryBookService {
    List<LibraryBookDto> getAll();
    LibraryBookDto getById(Long id);
    void addLibraryBook(LibraryBookDto dto);
    void updateLibraryBook(Long id, LibraryBookDto dto);
    boolean deleteLibraryBook(Long id);

    List<LibraryBookDto> getBooksByLibrary(Long libraryId);
    List<LibraryBookDto> getLibrariesByBook(Long bookId);
}