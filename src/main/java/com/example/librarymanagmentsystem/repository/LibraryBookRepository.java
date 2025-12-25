package com.example.librarymanagmentsystem.repository;

import com.example.librarymanagmentsystem.Model.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {
    List<LibraryBook> findByLibraryId(Long libraryId);
    List<LibraryBook> findByBookId(Long bookId);
}