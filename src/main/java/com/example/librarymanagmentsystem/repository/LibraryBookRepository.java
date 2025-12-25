package com.example.librarymanagmentsystem.repository;

import com.example.librarymanagmentsystem.Model.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {
    List<LibraryBook> findByLibraryId(Long libraryId);
    List<LibraryBook> findByBookId(Long bookId);
}