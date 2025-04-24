/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.userservice.controller;

import com.pintter.businessdomain.userservice.dto.UserDto;
import com.pintter.businessdomain.userservice.entities.User;
import com.pintter.businessdomain.userservice.mapper.UserMapper;
import com.pintter.businessdomain.userservice.repository.UserRepository;
import com.pintter.businessdomain.userservice.services.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Pc
 */
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
        List<UserDto> listUserDto = userMapper.toDtoList(userRepository.findAll());
        if (listUserDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sin usuarios registrados");
        } else {
            return ResponseEntity.ok(listUserDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id) {
        
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userMapper.toDto(opt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existe el usuario");
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        // Convertir DTO a Entidad
        User user = userMapper.toEntity(userDto);
        // Guardar en base de datos
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
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
    /*@GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/me")
    public UserDto getCurrentUser(Authentication authentication) {
        return userService.getCurrentUser(authentication);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }*/
}
