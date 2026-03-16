package com.example.backend.dto;

import com.example.backend.entity.RoleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private RoleType role;
}
