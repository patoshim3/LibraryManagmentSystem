package com.example.librarymanagmentsystem.controller;

import com.example.librarymanagmentsystem.dto.LibraryBookDto;
import com.example.librarymanagmentsystem.service.LibraryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library-book")
public class LibraryBookController {

    @Autowired
    private LibraryBookService libraryBookService;

    @GetMapping("/all")
    public ResponseEntity<List<LibraryBookDto>> getAll() {
        return new ResponseEntity<>(libraryBookService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<LibraryBookDto> getById(@PathVariable("id") Long id) {
        LibraryBookDto dto = libraryBookService.getById(id);
        return dto != null ? new ResponseEntity<>(dto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addLibraryBook(@RequestBody LibraryBookDto dto) {
        libraryBookService.addLibraryBook(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLibraryBook(@PathVariable("id") Long id, @RequestBody LibraryBookDto dto) {
        libraryBookService.updateLibraryBook(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibraryBook(@PathVariable("id") Long id) {
        if (libraryBookService.deleteLibraryBook(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Дополнительно: получить все книги по библиотеке
    @GetMapping("/library/{libraryId}")
    public ResponseEntity<List<LibraryBookDto>> getBooksByLibrary(@PathVariable Long libraryId) {
        return new ResponseEntity<>(libraryBookService.getBooksByLibrary(libraryId), HttpStatus.OK);
    }

    // Дополнительно: получить все библиотеки по книге
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<LibraryBookDto>> getLibrariesByBook(@PathVariable Long bookId) {
        return new ResponseEntity<>(libraryBookService.getLibrariesByBook(bookId), HttpStatus.OK);
    }
}