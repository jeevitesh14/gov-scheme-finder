package com.example.backend.service;

import com.example.backend.dto.SchemeDTO;
import com.example.backend.entity.CasteType;
import com.example.backend.entity.CategoryType;
import com.example.backend.entity.GenderType;
import com.example.backend.entity.Scheme;
import com.example.backend.repository.SchemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchemeService {

    private final SchemeRepository schemeRepository;

    public Page<SchemeDTO> getFilteredSchemes(
            CategoryType category, String state, GenderType gender, CasteType caste,
            Boolean disability, Boolean bpl, Boolean widow, Boolean minority,
            Double income, Pageable pageable) {
        
        return schemeRepository.findFiltered(category, state, gender, caste, disability, bpl, widow, minority, income, pageable)
                .map(this::convertToDTO);
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
        return SchemeDTO.builder()
                .id(scheme.getId())
                .name(scheme.getName())
                .description(scheme.getDescription())
                .category(scheme.getCategory())
                .state(scheme.getState())
                .gender(scheme.getGender())
                .caste(scheme.getCaste())
                .ageMin(scheme.getAgeMin())
                .ageMax(scheme.getAgeMax())
                .incomeLimit(scheme.getIncomeLimit())
                .disability(scheme.getDisability())
                .bpl(scheme.getBpl())
                .widow(scheme.getWidow())
                .minority(scheme.getMinority())
                .applyLink(scheme.getApplyLink())
                .build();
    }

    private Scheme convertToEntity(SchemeDTO dto) {
        return Scheme.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .state(dto.getState())
                .gender(dto.getGender())
                .caste(dto.getCaste())
                .ageMin(dto.getAgeMin())
                .ageMax(dto.getAgeMax())
                .incomeLimit(dto.getIncomeLimit())
                .disability(dto.getDisability())
                .bpl(dto.getBpl())
                .widow(dto.getWidow())
                .minority(dto.getMinority())
                .applyLink(dto.getApplyLink())
                .build();
    }
}
