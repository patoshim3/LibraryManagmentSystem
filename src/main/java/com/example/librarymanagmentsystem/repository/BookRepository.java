package com.example.librarymanagmentsystem.repository;

import com.example.librarymanagmentsystem.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
