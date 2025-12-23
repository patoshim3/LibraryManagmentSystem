package com.example.librarymanagmentsystem.repository;

import com.example.librarymanagmentsystem.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
