/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.follower.controller;

import com.pintter.businessdomain.follower.dto.FollowerDto;
import com.pintter.businessdomain.follower.entities.Follower;
import com.pintter.businessdomain.follower.exceptions.BusinessRuleException;
import com.pintter.businessdomain.follower.mapper.FollowerMapper;
import com.pintter.businessdomain.follower.repository.FollowerRepository;
import com.pintter.businessdomain.follower.services.FollowerService;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Pc
 */
@Slf4j
@RestController
@RequestMapping("/api/follower")
public class FollowerController {

    @Autowired
    private FollowerService followerService;
    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private FollowerMapper followerMapper;
    @GetMapping
    public ResponseEntity<?> getAllFollowers() {
        List<FollowerDto> listFollowerDto = followerService.getAllFollowers();
        if (listFollowerDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.ok(listFollowerDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFollowerById(@PathVariable(name = "id") Long id) {
        
        Optional<Follower> opt = followerService.getFollowerById(id);
        if (opt.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(followerMapper.toDto(opt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe el usuario");
        }
    }


    @GetMapping("/full/{uid}")
    public ResponseEntity<List<FollowerDto>> getFull(@PathVariable(name = "uid") Long uid) throws BusinessRuleException {
        List<Follower> follower = followerService.findByFollowerId(uid);
        List<FollowerDto> artworksList = followerMapper.toDtoList(follower);
        if (artworksList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.ok(artworksList);
        }
    }


    @PostMapping
    public ResponseEntity<?> createFollower(@RequestBody FollowerDto followerDto) throws BusinessRuleException, UnknownHostException {
        // Convertir DTO a Entidad
        FollowerDto follower = followerService.createFollower(followerDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(follower);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFollower(@PathVariable(name = "id") Long id) {
        Optional<Follower> find = followerRepository.findById(id);
        if (find.isPresent()) {
            FollowerDto followerDto = followerMapper.toDto(find.get());
            followerRepository.delete(find.get());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(followerDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No es aceptable");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFollower(@PathVariable(name="id") Long id, @RequestBody FollowerDto followerDto) throws BusinessRuleException {
        if (followerDto != null) {
            FollowerDto dto = followerService.updateFollower(id, followerDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No es aceptable");
        }
    }
}
