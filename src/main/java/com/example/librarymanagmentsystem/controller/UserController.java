package com.example.librarymanagmentsystem.controller;

import com.example.librarymanagmentsystem.Model.UserModel;
import com.example.librarymanagmentsystem.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final MyUserService myUserService;

    @GetMapping
    public String get123() {
        return "test";
    }

    @PostMapping("/register")
    public void register(@RequestBody UserModel model) {
        myUserService.register(model);
    }

    @GetMapping("/books")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> getAllBooks() {
        return new ResponseEntity<>("All books (protected endpoint)", HttpStatus.OK);
    }
}


