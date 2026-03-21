package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    private String name;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private String address;
    private String mobileNumber;
    private String state;
    private Double income;

    @Enumerated(EnumType.STRING)
    private CasteType caste;

    private String occupation;

    public UserProfile() {}

    public UserProfile(User user, String name, Integer age, GenderType gender, String address, String mobileNumber, String state, Double income, CasteType caste, String occupation) {
        this.user = user;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.state = state;
        this.income = income;
        this.caste = caste;
        this.occupation = occupation;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public GenderType getGender() { return gender; }
    public void setGender(GenderType gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public Double getIncome() { return income; }
    public void setIncome(Double income) { this.income = income; }
    public CasteType getCaste() { return caste; }
    public void setCaste(CasteType caste) { this.caste = caste; }
    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }
}
