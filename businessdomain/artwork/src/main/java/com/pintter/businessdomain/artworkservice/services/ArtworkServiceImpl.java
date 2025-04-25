/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.artworkservice.services;

import com.pintter.businessdomain.artworkservice.dto.ArtworkDto;
import com.pintter.businessdomain.artworkservice.entities.Artwork;
import com.pintter.businessdomain.artworkservice.exceptions.BusinessRuleException;
import com.pintter.businessdomain.artworkservice.mapper.ArtworkMapper;
import com.pintter.businessdomain.artworkservice.repository.ArtworkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Pc
 */
@Service
@Slf4j
public class ArtworkServiceImpl implements ArtworkService {

    @Autowired
    private ArtworkRepository artworkRepository;
    @Autowired
    private ArtworkMapper artworkMapper;
    // Add any required dependencies here (e.g., repositories, mappers)
    
    
    @Override
    public List<ArtworkDto> getAllArtworks() {
        // Implementation here
        return null;
    }

    @Override
    public ArtworkDto getArtworkById(Long id) {
        // Implementation here
        return null;
    }

    @Override
    public ArtworkDto getCurrentArtwork(Authentication auth) {
        // Implementation here
        return null;
    }

    @Override
    public ArtworkDto createArtwork(ArtworkDto artworkDto) {
        // Implementation here
        return null;
    }

    @Override
    public ArtworkDto updateArtwork(Long id, ArtworkDto artworkDto) throws BusinessRuleException {
        // Implementation here
        Optional<Artwork> opt = artworkRepository.findById(id);
        log.info("resArtwork:::::::"+opt.get());
        Artwork resArtwork = artworkMapper.toOptional(opt);
        if (resArtwork != null) {
            resArtwork.setId(id);
            resArtwork.setTitle(artworkDto.getTitle());
            resArtwork.setDescription(artworkDto.getDescription());
        } else {
            BusinessRuleException businessRuleException = new BusinessRuleException("0002", "Error validación. Transacion no localizada. ", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        }
        log.info("resArtwork:::::::"+resArtwork);
        ArtworkDto save = artworkMapper.toDto(artworkRepository.save(resArtwork));
        return save;
    }

    @Override
    public void deleteArtwork(Long id) {
        // Implementation here
    }
    
}
