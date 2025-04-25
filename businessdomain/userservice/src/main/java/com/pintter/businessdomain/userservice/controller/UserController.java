/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.userservice.controller;

import com.pintter.businessdomain.userservice.dto.UserDto;
import com.pintter.businessdomain.userservice.entities.User;
import com.pintter.businessdomain.userservice.exceptions.BusinessRuleException;
import com.pintter.businessdomain.userservice.mapper.UserMapper;
import com.pintter.businessdomain.userservice.repository.UserRepository;
import com.pintter.businessdomain.userservice.services.UserService;
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserDto> listUserDto = userService.getAllUsers();
        if (listUserDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sin usuarios registrados");
        } else {
            return ResponseEntity.ok(listUserDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id) {
        
        Optional<User> opt = userService.getUserById(id);
        if (opt.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userMapper.toDto(opt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe el usuario");
        }
    }

    @GetMapping("/full/{uid}")
    public ResponseEntity<?> getFull(@PathVariable(name = "uid") Long uid) throws BusinessRuleException {
        UserDto save = userService.getFull(uid);

        if (save != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws BusinessRuleException, UnknownHostException {
        // Convertir DTO a Entidad
        UserDto user = userService.createUser(userDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        Optional<User> find = userRepository.findById(id);
        if (find.isPresent()) {
            UserDto userDto = userMapper.toDto(find.get());
            userRepository.delete(find.get());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No es aceptable");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name="id") Long id, @RequestBody UserDto userDto) throws BusinessRuleException {
        if (userDto != null) {
            UserDto dto = userService.updateUser(id, userDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No es aceptable");
        }
    }
}
