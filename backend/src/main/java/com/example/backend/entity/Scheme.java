package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "schemes")
public class Scheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private CategoryType category;   // Education, Health, Agriculture, Housing, etc.

    private String state;      // All / specific state name

    @Enumerated(EnumType.STRING)
    private GenderType gender;     // All / Male / Female

    @Enumerated(EnumType.STRING)
    private CasteType caste;      // All / General / OBC / SC / ST

    private Integer ageMin;
    private Integer ageMax;
    private Double incomeLimit; // Annual income limit in INR (null = no limit)

    private Boolean disability;
    private Boolean bpl;
    private Boolean widow;
    private Boolean minority;

    private String applyLink;
}
