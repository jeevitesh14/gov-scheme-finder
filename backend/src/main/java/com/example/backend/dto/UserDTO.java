package com.example.backend.dto;

import com.example.backend.entity.RoleType;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private RoleType role;

    public UserDTO(Long id, String name, String email, RoleType role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public UserDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public RoleType getRole() { return role; }
    public void setRole(RoleType role) { this.role = role; }
}
