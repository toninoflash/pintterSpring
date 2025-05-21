/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.exhibition.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Pc
 */
@Data
public class ExhibitionDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime endDate;
    private LocalDateTime updaetAt;
    private Boolean isVirtual;
    private String location;
    private String status;
    private Long uid;
    private Long visibility;
    private Long views;
    private List<Long> artworksId;
}
