package com.backend.projet3OC.model;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank
    @Email
    private String login;

    @NotBlank
    private String password;
}
