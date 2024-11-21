package com.backend.projet3OC.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class HelloController {

    @GetMapping("/hello")
    @Operation(summary = "Dire bonjour", security = @SecurityRequirement(name = "bearerAuth"))
    public String sayHello() {
        return "Hello, World!";
    }
}
