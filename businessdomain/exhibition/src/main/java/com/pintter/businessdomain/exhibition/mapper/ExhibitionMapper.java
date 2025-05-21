/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pintter.businessdomain.exhibition.mapper;

import com.pintter.businessdomain.exhibition.dto.ExhibitionDto;
import com.pintter.businessdomain.exhibition.entities.Exhibition;
import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapper;

/**
 *
 * @author Pc
 */
@Mapper(componentModel = "spring")
public interface ExhibitionMapper {
    ExhibitionDto toDto(Exhibition follower);
    Exhibition toEntity(ExhibitionDto followerDto);
    List<ExhibitionDto> toDtoList(List<Exhibition> followers);
    default Exhibition toOptional(Optional<Exhibition> opt) {
        return opt.orElse(null); // o lanza una excepción si lo prefieres
    }
}
