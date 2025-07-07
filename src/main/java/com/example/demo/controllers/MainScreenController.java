package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainScreenController {

    @GetMapping("/mainscreen")
    public String home() {
        return "mainscreen";  // Renders templates/mainscreen.html
    }
}