package com.example.backend.controller;

import com.example.backend.dto.ApiResponse;
import com.example.backend.dto.UserProfileDTO;
import com.example.backend.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<UserProfileDTO>> getProfile(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(ApiResponse.success("Profile retrieved successfully", userProfileService.getProfileByEmail(email)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UserProfileDTO>> updateProfile(Authentication authentication, @RequestBody UserProfileDTO profileDTO) {
        String email = authentication.getName();
        return ResponseEntity.ok(ApiResponse.success("Profile updated successfully", userProfileService.updateProfile(email, profileDTO)));
    }
}
