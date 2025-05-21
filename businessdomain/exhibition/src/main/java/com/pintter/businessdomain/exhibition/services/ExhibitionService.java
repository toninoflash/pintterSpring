/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pintter.businessdomain.exhibition.services;

import com.pintter.businessdomain.exhibition.dto.ExhibitionDto;
import com.pintter.businessdomain.exhibition.entities.Exhibition;
import com.pintter.businessdomain.exhibition.exceptions.BusinessRuleException;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author Pc
 */

public interface ExhibitionService {
    List<ExhibitionDto> getAllExhibitions();
    Optional<Exhibition> getExhibitionById(Long id);
    ExhibitionDto createExhibition(ExhibitionDto followerDto);
    ExhibitionDto updateExhibition(Long id, ExhibitionDto followerDto) throws BusinessRuleException;
    List<ExhibitionDto> findByUid(Long uid);

    void deleteExhibition(Long id);
}
