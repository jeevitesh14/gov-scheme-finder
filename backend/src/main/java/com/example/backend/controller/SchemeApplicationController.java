package com.example.backend.controller;

import com.example.backend.dto.ApiResponse;
import com.example.backend.dto.SchemeApplicationDTO;
import com.example.backend.service.SchemeApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin
public class SchemeApplicationController {

    private final SchemeApplicationService applicationService;

    public SchemeApplicationController(SchemeApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/apply/{schemeId}")
    public ResponseEntity<ApiResponse<Void>> applyForScheme(@PathVariable Long schemeId, Authentication authentication) {
        try {
            applicationService.applyForScheme(authentication.getName(), schemeId);
            return ResponseEntity.ok(ApiResponse.success("Application submitted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }


    @GetMapping("/my-applications")
    public ResponseEntity<List<SchemeApplicationDTO>> getMyApplications(Authentication authentication) {
        return ResponseEntity.ok(applicationService.getUserApplications(authentication.getName()));
    }
}
