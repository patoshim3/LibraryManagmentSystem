package com.example.librarymanagmentsystem.controller;

import com.example.librarymanagmentsystem.Model.UserModel;
import com.example.librarymanagmentsystem.service.MyUserService;
import lombok.RequiredArgsConstructor;
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
}