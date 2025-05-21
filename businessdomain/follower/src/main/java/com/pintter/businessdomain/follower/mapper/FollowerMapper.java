/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pintter.businessdomain.follower.mapper;

import com.pintter.businessdomain.follower.dto.FollowerDto;
import com.pintter.businessdomain.follower.entities.Follower;
import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapper;

/**
 *
 * @author Pc
 */
@Mapper(componentModel = "spring")
public interface FollowerMapper {
    FollowerDto toDto(Follower follower);
    Follower toEntity(FollowerDto followerDto);
    List<FollowerDto> toDtoList(List<Follower> followers);
    default Follower toOptional(Optional<Follower> opt) {
        return opt.orElse(null); // o lanza una excepción si lo prefieres
    }
}
