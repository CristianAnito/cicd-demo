package com.example.cicd_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hola() {
        return "Hola desde la API";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

}