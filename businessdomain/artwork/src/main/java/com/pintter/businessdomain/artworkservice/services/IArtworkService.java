/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pintter.businessdomain.artworkservice.services;

import com.pintter.businessdomain.artworkservice.dto.ArtworkDto;
import com.pintter.businessdomain.artworkservice.exceptions.BusinessRuleException;

import java.util.List;

/**
 *
 * @author Pc
 */

public interface IArtworkService {
    List<ArtworkDto> getAllArtworks();
    ArtworkDto getArtworkById(Long id);
    ArtworkDto createArtwork(ArtworkDto artworkDto);
    ArtworkDto updateArtwork(Long id, ArtworkDto artworkDto) throws BusinessRuleException;
    void deleteArtwork(Long id);
}
