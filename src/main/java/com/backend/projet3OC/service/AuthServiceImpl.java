package com.backend.projet3OC.service;

import com.backend.projet3OC.dto.RegisterRequest;
import com.backend.projet3OC.dto.LoginRequest;
import com.backend.projet3OC.dto.UserResponseDTO;
import com.backend.projet3OC.exception.InvalidCredentialsException;
import com.backend.projet3OC.model.User;
import com.backend.projet3OC.repository.UserRepository;
import com.backend.projet3OC.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        logger.info("Attempting to authenticate user with email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.warn("User not found with email: {}", email);
                    return new InvalidCredentialsException("Invalid email or password");
                });

        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail());
            logger.info("User {} authenticated successfully, token generated", email);
            return token;
        } else {
            logger.warn("Password mismatch for user {}", email);
            throw new InvalidCredentialsException("Invalid email or password");
        }
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            logger.warn("Attempt to register with existing email: {}", registerRequest.getEmail());
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setName(registerRequest.getName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        logger.info("User registered successfully with email: {}", registerRequest.getEmail());
    }

    @Override
    public UserResponseDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            logger.warn("Attempt to access /me without authentication");
            throw new InvalidCredentialsException("User is not authenticated");
        }

        String email = (String) authentication.getPrincipal();
        logger.info("Fetching current user details for email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("User not found with email: {}", email);
                    return new RuntimeException("User not found");
                });

        logger.info("User found: {}", email);
        return convertToUserResponseDTO(user);
    }

    private UserResponseDTO convertToUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreated_at());
        dto.setUpdatedAt(user.getUpdated_at());
        logger.info("Converted User to UserResponseDTO for user: {}", user.getEmail());
        return dto;
    }
}
