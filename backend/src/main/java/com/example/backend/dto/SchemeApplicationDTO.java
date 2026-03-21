package com.example.backend.dto;

import com.example.backend.entity.ApplicationStatus;
import java.time.LocalDateTime;

public class SchemeApplicationDTO {
    private Long id;
    private Long schemeId;
    private String schemeName;
    private Long userId;
    private String userName;
    private LocalDateTime applicationDate;
    private ApplicationStatus status;

    public SchemeApplicationDTO(Long id, Long schemeId, String schemeName, Long userId, String userName, LocalDateTime applicationDate, ApplicationStatus status) {
        this.id = id;
        this.schemeId = schemeId;
        this.schemeName = schemeName;
        this.userId = userId;
        this.userName = userName;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    public SchemeApplicationDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getSchemeId() { return schemeId; }
    public void setSchemeId(Long schemeId) { this.schemeId = schemeId; }
    public String getSchemeName() { return schemeName; }
    public void setSchemeName(String schemeName) { this.schemeName = schemeName; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public LocalDateTime getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDateTime applicationDate) { this.applicationDate = applicationDate; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
}
