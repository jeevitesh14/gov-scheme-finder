package com.example.backend.dto;

import com.example.backend.entity.CasteType;
import com.example.backend.entity.GenderType;

public class UserProfileDTO {
    private String name;
    private Integer age;
    private GenderType gender;
    private String address;
    private String mobileNumber;
    private String state;
    private Double income;
    private CasteType caste;
    private String occupation;

    public UserProfileDTO() {}

    public UserProfileDTO(String name, Integer age, GenderType gender, String address, String mobileNumber, String state, Double income, CasteType caste, String occupation) {
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
