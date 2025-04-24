/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.userservice.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author Pc
 */
@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String lastname;
    private String bio;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<String> roles;
    
}
