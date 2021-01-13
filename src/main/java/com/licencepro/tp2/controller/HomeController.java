package com.licencepro.tp2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/home")
public class HomeController {

    @GetMapping
    public String getHome() {
        return "Hello from home page !";
    }

    @GetMapping("/{text}")
    public String getText(@PathVariable String text) {
        return String.format("Texte à afficher: %s", text);
    }
}
