package com.backend.projet3OC.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterRequest {

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String name;

    @NotBlank
    @Size(min = 6, message = "Password should have at least 6 characters")
    private final String password;

    public RegisterRequest(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
