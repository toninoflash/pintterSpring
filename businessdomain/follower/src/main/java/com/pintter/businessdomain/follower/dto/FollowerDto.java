/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.follower.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import lombok.Data;

/**
 *
 * @author Pc
 */
@Data
public class FollowerDto {
    private Long id;
    private Long followerId;// ID del usuario que sigue
    private Long followedId;// ID del usuario seguido
    private LocalDateTime createAt;// Fecha en que comenzó a seguir
    private String status;// Fecha en que comenzó a seguir
}
