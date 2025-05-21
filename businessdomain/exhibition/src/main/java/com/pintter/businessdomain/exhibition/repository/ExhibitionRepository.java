/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pintter.businessdomain.exhibition.repository;

import com.pintter.businessdomain.exhibition.dto.ExhibitionDto;
import com.pintter.businessdomain.exhibition.entities.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Pc
 */
public interface ExhibitionRepository  extends JpaRepository<Exhibition, Long> {

    List<Exhibition> findByUid(Long uid);
}
