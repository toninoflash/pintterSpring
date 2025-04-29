/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.artworkservice.controller;

import com.pintter.businessdomain.artworkservice.dto.ArtworkDto;
import com.pintter.businessdomain.artworkservice.entities.Artwork;
import com.pintter.businessdomain.artworkservice.exceptions.BusinessRuleException;
import com.pintter.businessdomain.artworkservice.mapper.ArtworkMapper;
import com.pintter.businessdomain.artworkservice.repository.ArtworkRepository;
import com.pintter.businessdomain.artworkservice.services.IArtworkService;
import com.pintter.businessdomain.artworkservice.services.ICloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Pc
 */
@RestController
@RequestMapping("/api/artwork")
public class ArtworkController {

    @Autowired
    private IArtworkService artworkService;
    @Autowired
    private ArtworkRepository artworkRepository;
    @Autowired
    private ArtworkMapper artworkMapper;
    @Autowired
    private ICloudinaryService cloudinaryService;

    @GetMapping
    public ResponseEntity<?> getAllArtworks() {
        List<ArtworkDto> listArtworkDto = artworkMapper.toDtoList(artworkRepository.findAll());
        if (listArtworkDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.ok(listArtworkDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArtworkById(@PathVariable(name = "id") Long id) {
        
        Optional<Artwork> opt = artworkRepository.findById(id);
        if (opt.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(artworkMapper.toDto(opt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe la obra");
        }
    }

    @GetMapping("/user/full")
    public ResponseEntity<?> get(@RequestParam(name = "uid") Long uid) {
        List<Artwork> artworks = artworkRepository.findByUid(uid);
        List<ArtworkDto> artworksList = artworkMapper.toDtoList(artworks);

        if (artworksList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.ok(artworksList);
        }
    }

    @PostMapping
    public ResponseEntity<?> createArtwork(@RequestBody ArtworkDto artworkDto) {
        // Convertir DTO a Entidad
        Artwork artwork = artworkMapper.toEntity(artworkDto);
        // Guardar en base de datos
        Artwork savedArtwork = artworkRepository.save(artwork);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArtwork);
    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = cloudinaryService.uploadFile(file);
            return ResponseEntity.ok(Collections.singletonMap("url", imageUrl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading image: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtwork(@PathVariable(name = "id") Long id) {
        Optional<Artwork> find = artworkRepository.findById(id);
        if (find.isPresent()) {
            ArtworkDto artworkDto = artworkMapper.toDto(find.get());
            artworkRepository.delete(find.get());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(artworkDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No es aceptable");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArtwork(@PathVariable(name="id") Long id, @RequestBody ArtworkDto artworkDto) throws BusinessRuleException {
        if (artworkDto != null) {
            ArtworkDto dto = artworkService.updateArtwork(id, artworkDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No es aceptable");
        }
    }
    
}
