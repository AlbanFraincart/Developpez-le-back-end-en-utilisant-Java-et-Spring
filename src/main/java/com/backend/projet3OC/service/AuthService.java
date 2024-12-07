package com.backend.projet3OC.service;

import com.backend.projet3OC.dto.LoginRequest;
import com.backend.projet3OC.dto.RegisterRequest;
import com.backend.projet3OC.dto.UserResponseDTO;

//define methods for users authentication et registration
public interface AuthService {
    String login(LoginRequest loginRequest);
    void register(RegisterRequest registerRequest);
    UserResponseDTO getCurrentUser();
}
