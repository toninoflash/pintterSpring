/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.follower.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 *
 * @author Pc
 */
@Entity
@Data
@Table(name = "users")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long followerId;// ID del usuario que sigue
    private Long followedId;// ID del usuario seguido
    private LocalDateTime createAt;// Fecha en que comenzó a seguir
    private String status;// Fecha en que comenzó a seguir

}
