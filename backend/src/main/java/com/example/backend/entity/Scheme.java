package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "schemes")
public class Scheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private CategoryType category; 

    private String state; 

    @Enumerated(EnumType.STRING)
    private GenderType gender; 

    @Enumerated(EnumType.STRING)
    private CasteType caste; 

    private Integer ageMin;
    private Integer ageMax;
    private Double incomeLimit; 

    private Boolean disability;
    private Boolean bpl;
    private Boolean widow;
    private Boolean minority;

    private String applyLink;

    public Scheme(Long id, String name, String description, CategoryType category, String state, GenderType gender, CasteType caste, Integer ageMin, Integer ageMax, Double incomeLimit, Boolean disability, Boolean bpl, Boolean widow, Boolean minority, String applyLink) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.state = state;
        this.gender = gender;
        this.caste = caste;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.incomeLimit = incomeLimit;
        this.disability = disability;
        this.bpl = bpl;
        this.widow = widow;
        this.minority = minority;
        this.applyLink = applyLink;
    }

    public Scheme() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public CategoryType getCategory() { return category; }
    public void setCategory(CategoryType category) { this.category = category; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public GenderType getGender() { return gender; }
    public void setGender(GenderType gender) { this.gender = gender; }
    public CasteType getCaste() { return caste; }
    public void setCaste(CasteType caste) { this.caste = caste; }
    public Integer getAgeMin() { return ageMin; }
    public void setAgeMin(Integer ageMin) { this.ageMin = ageMin; }
    public Integer getAgeMax() { return ageMax; }
    public void setAgeMax(Integer ageMax) { this.ageMax = ageMax; }
    public Double getIncomeLimit() { return incomeLimit; }
    public void setIncomeLimit(Double incomeLimit) { this.incomeLimit = incomeLimit; }
    public Boolean getDisability() { return disability; }
    public void setDisability(Boolean disability) { this.disability = disability; }
    public Boolean getBpl() { return bpl; }
    public void setBpl(Boolean bpl) { this.bpl = bpl; }
    public Boolean getWidow() { return widow; }
    public void setWidow(Boolean widow) { this.widow = widow; }
    public Boolean getMinority() { return minority; }
    public void setMinority(Boolean minority) { this.minority = minority; }
    public String getApplyLink() { return applyLink; }
    public void setApplyLink(String applyLink) { this.applyLink = applyLink; }
}
