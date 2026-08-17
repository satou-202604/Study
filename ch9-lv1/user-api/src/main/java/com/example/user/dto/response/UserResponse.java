package com.example.user.dto.response;

import java.time.LocalDateTime;

import com.example.user.entity.UserEntity;

public class UserResponse {

    private Integer id;
    private String username;
    private String email;
    private String fullName;
    private String department;
    private String role;
    private Boolean isActive;
    private LocalDateTime createdAt;

    public static UserResponse from(UserEntity entity) {
        UserResponse response = new UserResponse();
        response.id         = entity.getId();
        response.username   = entity.getUsername();
        response.email      = entity.getEmail();
        response.fullName   = entity.getFullName();
        response.department = entity.getDepartment();
        response.role       = entity.getRole();
        response.isActive   = entity.getIsActive();
        response.createdAt  = entity.getCreatedAt();
        return response;
    }

    public Integer getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getFullName() { return fullName; }
    public String getDepartment() { return department; }
    public String getRole() { return role; }
    public Boolean getIsActive() { return isActive; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}