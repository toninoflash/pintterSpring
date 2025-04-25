/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.artworkservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 * @author Pc
 */
@Data
public class ArtworkDto {
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private String imageUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    private Long uid; // Referencia al usuario que subió la obra (desde user-service)

    private String category;
    private String status;
    
}
