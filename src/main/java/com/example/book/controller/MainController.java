package com.example.book.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MainController {

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/help")
    public String help() {
        return "help";
    }
}
