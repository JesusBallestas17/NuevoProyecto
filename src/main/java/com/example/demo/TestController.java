package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("/public/hello")
    public String publicEndpoint() {
        return "Este es un endpoint público";
    }

    @GetMapping("/admin/dashboard")
    public String adminEndpoint() {
        return "Bienvenido al panel de administración";
    }
}