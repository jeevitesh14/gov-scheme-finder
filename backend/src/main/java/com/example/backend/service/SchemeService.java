package com.example.backend.service;

import com.example.backend.dto.SchemeDTO;
import com.example.backend.entity.*;
import com.example.backend.repository.SchemeRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.UserProfileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SchemeService {

    private final SchemeRepository schemeRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public SchemeService(SchemeRepository schemeRepository, UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.schemeRepository = schemeRepository;
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    public Page<SchemeDTO> getFilteredSchemes(
            String keyword, CategoryType category, String state, GenderType gender, CasteType caste,
            Boolean disability, Boolean bpl, Boolean widow, Boolean minority,
            Double income, Integer age, Pageable pageable) {
        
        return schemeRepository.findFiltered(keyword, category, state, gender, caste, disability, bpl, widow, minority, income, age, pageable)
                .map(this::convertToDTO);
    }

    public Page<SchemeDTO> getEligibleSchemes(String email, Pageable pageable) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        UserProfile profile = userProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Profile not found. Please complete your profile first."));

        return schemeRepository.findFiltered(
                null, // keyword
                null, // category
                profile.getState(),
                profile.getGender(),
                profile.getCaste(),
                null, // disability (can be added to profile later if needed)
                null, // bpl
                null, // widow
                null, // minority
                profile.getIncome(),
                profile.getAge(),
                pageable
        ).map(this::convertToDTO);
    }

    public SchemeDTO createScheme(SchemeDTO schemeDTO) {
        Scheme scheme = convertToEntity(schemeDTO);
        return convertToDTO(schemeRepository.save(scheme));
    }

    public SchemeDTO updateScheme(Long id, SchemeDTO schemeDetails) {
        Scheme scheme = schemeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scheme not found with id: " + id));

        scheme.setName(schemeDetails.getName());
        scheme.setDescription(schemeDetails.getDescription());
        scheme.setCategory(schemeDetails.getCategory());
        scheme.setState(schemeDetails.getState());
        scheme.setGender(schemeDetails.getGender());
        scheme.setCaste(schemeDetails.getCaste());
        scheme.setAgeMin(schemeDetails.getAgeMin());
        scheme.setAgeMax(schemeDetails.getAgeMax());
        scheme.setIncomeLimit(schemeDetails.getIncomeLimit());
        scheme.setDisability(schemeDetails.getDisability());
        scheme.setBpl(schemeDetails.getBpl());
        scheme.setWidow(schemeDetails.getWidow());
        scheme.setMinority(schemeDetails.getMinority());
        scheme.setApplyLink(schemeDetails.getApplyLink());

        return convertToDTO(schemeRepository.save(scheme));
    }

    public void deleteScheme(Long id) {
        schemeRepository.deleteById(id);
    }

    private SchemeDTO convertToDTO(Scheme scheme) {
        return new SchemeDTO(
                scheme.getId(),
                scheme.getName(),
                scheme.getDescription(),
                scheme.getCategory(),
                scheme.getState(),
                scheme.getGender(),
                scheme.getCaste(),
                scheme.getAgeMin(),
                scheme.getAgeMax(),
                scheme.getIncomeLimit(),
                scheme.getDisability(),
                scheme.getBpl(),
                scheme.getWidow(),
                scheme.getMinority(),
                scheme.getApplyLink()
        );
    }

    private Scheme convertToEntity(SchemeDTO dto) {
        Scheme scheme = new Scheme();
        scheme.setName(dto.getName());
        scheme.setDescription(dto.getDescription());
        scheme.setCategory(dto.getCategory());
        scheme.setState(dto.getState());
        scheme.setGender(dto.getGender());
        scheme.setCaste(dto.getCaste());
        scheme.setAgeMin(dto.getAgeMin());
        scheme.setAgeMax(dto.getAgeMax());
        scheme.setIncomeLimit(dto.getIncomeLimit());
        scheme.setDisability(dto.getDisability());
        scheme.setBpl(dto.getBpl());
        scheme.setWidow(dto.getWidow());
        scheme.setMinority(dto.getMinority());
        scheme.setApplyLink(dto.getApplyLink());
        return scheme;
    }
}
