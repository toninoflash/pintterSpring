/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pintter.businessdomain.artworkservice.repository;

import com.pintter.businessdomain.artworkservice.entities.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @author Pc
 */
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
    List<Artwork> findByUid(Long userId);
    
}
