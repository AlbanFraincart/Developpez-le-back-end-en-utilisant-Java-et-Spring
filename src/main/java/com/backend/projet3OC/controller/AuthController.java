package com.backend.projet3OC.controller;

import com.backend.projet3OC.model.LoginRequest;
import com.backend.projet3OC.model.RegisterRequest;
import com.backend.projet3OC.model.User;
import com.backend.projet3OC.service.AuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/email")
    public Map<String, String> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest.getLogin(), loginRequest.getPassword());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    @PostMapping("/register")
    public Map<String, String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setName(registerRequest.getName());
        user.setPassword(registerRequest.getPassword());

        authService.register(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "success");
        return response;
    }
}
