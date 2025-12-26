package com.example.librarymanagmentsystem.service;

import com.example.librarymanagmentsystem.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAll();

    BookDto getBookById(Long id);

    void addBook(BookDto dto);

    void updateBook(Long id, BookDto dto);

    boolean deleteBook(Long id);
}
