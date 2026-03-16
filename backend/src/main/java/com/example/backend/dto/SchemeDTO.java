package com.example.backend.dto;

import com.example.backend.entity.CategoryType;
import com.example.backend.entity.CasteType;
import com.example.backend.entity.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchemeDTO {
    private Long id;
    private String name;
    private String description;
    private CategoryType category;
    private String state;
    private GenderType gender;
    private CasteType caste;
    private Integer ageMin;
    private Integer ageMax;
    private Double incomeLimit;
    private Boolean disability;
    private Boolean bpl;
    private Boolean widow;
    private Boolean minority;
    private String applyLink;
}
