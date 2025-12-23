package com.example.librarymanagmentsystem.controller;

import com.example.librarymanagmentsystem.dto.AuthorDto;
import com.example.librarymanagmentsystem.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(authorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(authorService.getAuthorByID(id));
    }

    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestBody AuthorDto authorDto){
        authorService.addAuthor(authorDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable(name = "id") Long id,
                                          @RequestBody AuthorDto authorDto){
        authorService.updateAuthor(id, authorDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAuthor(@PathVariable(name = "id") Long id){
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
