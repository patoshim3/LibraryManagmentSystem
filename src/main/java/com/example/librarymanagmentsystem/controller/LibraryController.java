package com.example.librarymanagmentsystem.controller;

import com.example.librarymanagmentsystem.dto.LibraryDto;
import com.example.librarymanagmentsystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/all")
    public ResponseEntity<List<LibraryDto>> getAll() {
        return new ResponseEntity<>(libraryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<LibraryDto> getById(@PathVariable("id") Long id) {
        LibraryDto dto = libraryService.getById(id);
        return dto != null ? new ResponseEntity<>(dto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addLibrary(@RequestBody LibraryDto dto) {
        libraryService.addLibrary(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLibrary(@PathVariable("id") Long id, @RequestBody LibraryDto dto) {
        libraryService.updateLibrary(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable("id") Long id) {
        if (libraryService.deleteLibrary(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}