/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.exhibition.controller;

import com.pintter.businessdomain.exhibition.dto.ExhibitionDto;
import com.pintter.businessdomain.exhibition.entities.Exhibition;
import com.pintter.businessdomain.exhibition.exceptions.BusinessRuleException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pintter.businessdomain.exhibition.mapper.ExhibitionMapper;
import com.pintter.businessdomain.exhibition.repository.ExhibitionRepository;
import com.pintter.businessdomain.exhibition.services.ExhibitionService;

/**
 *
 * @author Pc
 */
@Slf4j
@RestController
@RequestMapping("/api/exhibition")
public class ExhibitionController {

    @Autowired
    private ExhibitionService exhibitionService;
    @Autowired
    private ExhibitionRepository exhibitionRepository;
    @Autowired
    private ExhibitionMapper exhibitionMapper;
    @GetMapping
    public ResponseEntity<?> getAllExhibitions() {
        List<ExhibitionDto> listExhibitionDto = exhibitionService.getAllExhibitions();
        if (listExhibitionDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.ok(listExhibitionDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExhibitionById(@PathVariable(name = "id") Long id) {
        
        Optional<Exhibition> opt = exhibitionService.getExhibitionById(id);
        if (opt.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(exhibitionMapper.toDto(opt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe el usuario");
        }
    }


    @GetMapping("/full/{uid}")
    public ResponseEntity<List<ExhibitionDto>> getFull(@PathVariable(name = "uid") Long uid) throws BusinessRuleException {
        List<ExhibitionDto> exhibitionDtoList = exhibitionService.findByUid(uid);
        if (exhibitionDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.ok(exhibitionDtoList);
        }
    }


    @PostMapping
    public ResponseEntity<?> createExhibition(@RequestBody ExhibitionDto exhibitionDto) throws BusinessRuleException, UnknownHostException {
        // Convertir DTO a Entidad
        ExhibitionDto exhibition = exhibitionService.createExhibition(exhibitionDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(exhibition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExhibition(@PathVariable(name = "id") Long id) {
        Optional<Exhibition> find = exhibitionRepository.findById(id);
        if (find.isPresent()) {
            ExhibitionDto exhibitionDto = exhibitionMapper.toDto(find.get());
            exhibitionRepository.delete(find.get());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(exhibitionDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No es aceptable");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExhibition(@PathVariable(name="id") Long id, @RequestBody ExhibitionDto exhibitionDto) throws BusinessRuleException {
        if (exhibitionDto != null) {
            ExhibitionDto dto = exhibitionService.updateExhibition(id, exhibitionDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No es aceptable");
        }
    }
}
