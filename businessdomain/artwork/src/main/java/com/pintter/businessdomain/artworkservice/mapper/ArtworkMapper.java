/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pintter.businessdomain.artworkservice.mapper;

import com.pintter.businessdomain.artworkservice.dto.ArtworkDto;
import com.pintter.businessdomain.artworkservice.entities.Artwork;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Pc
 */
@Mapper(componentModel = "spring")
public interface ArtworkMapper {
    ArtworkDto toDto(Artwork artwork);
    Artwork toEntity(ArtworkDto artworkDto);
    List<ArtworkDto> toDtoList(List<Artwork> artworks);
    Artwork toOptional(Optional<Artwork> opt);
}
