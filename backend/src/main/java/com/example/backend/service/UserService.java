package com.example.backend.service;

import com.example.backend.dto.*;
import com.example.backend.entity.*;
import com.example.backend.repository.UserRepository;
import com.example.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ApiResponse<UserDTO> register(User user) {
        String email = user.getEmail() == null ? null : user.getEmail().trim().toLowerCase();
        String rawPassword = user.getPassword() == null ? null : user.getPassword().trim();
        
        if (email == null || rawPassword == null) {
            return ApiResponse.error("Email and password are required");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            return ApiResponse.error("Email already registered");
        }

        user.setEmail(email);
        user.setRole(RoleType.USER);
        if ("admin@gov.in".equalsIgnoreCase(email)) {
            user.setRole(RoleType.ADMIN);
        }

        user.setPassword(passwordEncoder.encode(rawPassword));
        User savedUser = userRepository.save(user);

        return ApiResponse.success("User registered successfully", convertToDTO(savedUser));
    }

    public ApiResponse<LoginResponse> login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail() == null ? null : loginRequest.getEmail().trim().toLowerCase();
        String rawPassword = loginRequest.getPassword() == null ? null : loginRequest.getPassword().trim();

        if (email == null || rawPassword == null) {
            return ApiResponse.error("Email and password are required");
        }

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User u = userOpt.get();
            if (passwordEncoder.matches(rawPassword, u.getPassword())) {
                String token = jwtUtil.generateToken(u.getEmail(), u.getRole().name());
                LoginResponse loginResponse = LoginResponse.builder()
                        .token(token)
                        .role(u.getRole().name())
                        .email(u.getEmail())
                        .name(u.getName())
                        .build();
                return ApiResponse.success("Login successful", loginResponse);
            } else {
                return ApiResponse.error("Invalid password");
            }
        }
        return ApiResponse.error("User not found");
    }

    public UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
