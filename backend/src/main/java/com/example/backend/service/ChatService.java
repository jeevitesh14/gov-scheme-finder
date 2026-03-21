package com.example.backend.service;

import com.example.backend.dto.SchemeDTO;
import com.example.backend.entity.*;
import com.example.backend.repository.SchemeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final SchemeRepository schemeRepository;

    private static final List<String> STATES = Arrays.asList(
        "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", 
        "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", 
        "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", 
        "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"
    );

    public ChatService(SchemeRepository schemeRepository) {
        this.schemeRepository = schemeRepository;
    }

    public List<SchemeDTO> querySchemes(String query) {
        String low = query.toLowerCase();
        String keyword = null;
        CategoryType category = null;
        String state = null;
        GenderType gender = null; // null means ALL in our query logic now
        CasteType caste = null;
        Boolean disability = null;
        Boolean bpl = null;
        Boolean widow = null;

        // Extract State
        for (String s : STATES) {
            if (low.contains(s.toLowerCase())) {
                state = s;
                break;
            }
        }

        // Extract Category & Specific Keywords
        if (containsAny(low, "farmer", "kisan", "crop", "agriculture", "land")) category = CategoryType.AGRICULTURE;
        else if (containsAny(low, "student", "scholarship", "school", "college", "education", "study")) category = CategoryType.EDUCATION;
        else if (containsAny(low, "health", "medical", "hospital", "doctor", "medicine", "treatment", "aarogyasri", "ayushman")) category = CategoryType.HEALTH;
        else if (containsAny(low, "house", "home", "housing", "pucca", "awas")) category = CategoryType.HOUSING;
        else if (containsAny(low, "loan", "finance", "credit", "money", "vaddi")) category = CategoryType.FINANCE;
        else if (containsAny(low, "food", "ration", "rice", "wheat", "meal", "bhagya", "bhojan")) category = CategoryType.FOOD;
        else if (containsAny(low, "women", "girl", "female", "lady", "shakti", "mahila", "kanya", "amma", "vodi", "kanyashree")) {
            category = CategoryType.WOMEN_CHILD_WELFARE;
            gender = GenderType.FEMALE;
        }
        else if (containsAny(low, "work", "job", "employment", "unemployed", "nrega", "rozgar")) category = CategoryType.EMPLOYMENT;
        else if (containsAny(low, "skill", "training", "vocational", "kaushal", "development")) category = CategoryType.SKILL_DEVELOPMENT;
        else if (containsAny(low, "business", "entrepreneur", "startup", "self-employed")) category = CategoryType.ENTREPRENEURSHIP;
        else if (containsAny(low, "pension", "old age", "elderly", "senior", "retired")) category = CategoryType.SOCIAL_WELFARE;

        // Specific Eligibility Markers
        if (containsAny(low, "widow")) {
            widow = true;
            category = CategoryType.SOCIAL_WELFARE;
        }
        if (containsAny(low, "disabled", "handicap", "divyang", "blind", "deaf")) {
            disability = true;
            category = CategoryType.SOCIAL_WELFARE;
        }
        if (containsAny(low, "bpl", "poor", "below poverty", "low income")) bpl = true;

        // If no category was mapped, use the query as a keyword
        if (category == null && state == null && gender == null && disability == null && bpl == null && widow == null) {
            keyword = query;
        }

        System.out.println("Chat Query: " + query);
        System.out.println("Parsed Params - State: " + state + ", Category: " + category + ", Gender: " + gender + ", Keyword: " + keyword);

        try {
            Pageable pageable = PageRequest.of(0, 5, Sort.by("id").descending());
            Page<Scheme> schemes = schemeRepository.findFiltered(
                keyword, category, state, gender, caste, disability, bpl, widow, null, null, null, pageable
            );
            System.out.println("Found " + schemes.getTotalElements() + " schemes");

            return schemes.getContent().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("ChatService Error: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private boolean containsAny(String input, String... keywords) {
        for (String k : keywords) {
            if (input.contains(k)) return true;
        }
        return false;
    }

    private SchemeDTO convertToDTO(Scheme s) {
        return new SchemeDTO(
            s.getId(), s.getName(), s.getDescription(), s.getCategory(), s.getState(),
            s.getGender(), s.getCaste(), s.getAgeMin(), s.getAgeMax(), s.getIncomeLimit(),
            s.getDisability(), s.getBpl(), s.getWidow(), s.getMinority(), s.getApplyLink()
        );
    }
}
