package com.example.librarymanagmentsystem.repository;

import com.example.librarymanagmentsystem.Model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}