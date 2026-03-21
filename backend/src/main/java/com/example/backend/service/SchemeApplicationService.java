package com.example.backend.service;

import com.example.backend.dto.SchemeApplicationDTO;
import com.example.backend.entity.ApplicationStatus;
import com.example.backend.entity.Scheme;
import com.example.backend.entity.SchemeApplication;
import com.example.backend.entity.User;
import com.example.backend.repository.SchemeApplicationRepository;
import com.example.backend.repository.SchemeRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchemeApplicationService {

    private final SchemeApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final SchemeRepository schemeRepository;

    public SchemeApplicationService(SchemeApplicationRepository applicationRepository, UserRepository userRepository, SchemeRepository schemeRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.schemeRepository = schemeRepository;
    }

    @Transactional
    public void applyForScheme(String email, Long schemeId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Scheme scheme = schemeRepository.findById(schemeId)
                .orElseThrow(() -> new RuntimeException("Scheme not found"));

        if (applicationRepository.existsByUserAndScheme(user, scheme)) {
            throw new RuntimeException("You have already applied for this scheme");
        }

        SchemeApplication application = new SchemeApplication();
        application.setUser(user);
        application.setScheme(scheme);
        application.setApplicationDate(LocalDateTime.now());
        application.setStatus(ApplicationStatus.APPLIED);

        applicationRepository.save(application);
    }

    public List<SchemeApplicationDTO> getUserApplications(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return applicationRepository.findByUser(user).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SchemeApplicationDTO convertToDTO(SchemeApplication application) {
        return new SchemeApplicationDTO(
                application.getId(),
                application.getScheme().getId(),
                application.getScheme().getName(),
                application.getUser().getId(),
                application.getUser().getName(),
                application.getApplicationDate(),
                application.getStatus()
        );
    }
}
