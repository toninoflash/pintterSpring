/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.userservice.services;

import com.pintter.businessdomain.userservice.dto.UserDto;
import com.pintter.businessdomain.userservice.entities.User;
import com.pintter.businessdomain.userservice.exceptions.BusinessRuleException;
import com.pintter.businessdomain.userservice.mapper.UserMapper;
import com.pintter.businessdomain.userservice.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.pintter.businessdomain.userservice.transactions.BusinessTransactions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author Pc
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private BusinessTransactions businessTransactions;
    // Add any required dependencies here (e.g., repositories, mappers)

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> listUserDto = userMapper.toDtoList(userRepository.findAll());

        return listUserDto;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> opt = userRepository.findById(id);
        return opt;
    }

    @Override
    public UserDto getCurrentUser(Authentication auth) {
        // Implementation here
        return null;
    }

    @Override
    public UserDto getFull(Long id) throws BusinessRuleException  {
        // Implementation
        Optional<User> optUser = userRepository.findById(id);
        User user = userMapper.toOptional(optUser);
        List<?> artwork = businessTransactions.getTransactions(id);
        user.setArtWork(artwork);
        if (user != null) {
            UserDto dto = userMapper.toDto(user);
            dto.setArtWork(artwork);
            return dto;
        } else {
            BusinessRuleException businessRuleException = new BusinessRuleException("0002", "Error validación. Transacion no localizada. ", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        
    log.info("CREATE USER INICIADO: " + userDto);

        User user = userMapper.toEntity(userDto);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        log.info("Usuario creado: " + user);

        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) throws BusinessRuleException {
        // Implementation here
        Optional<User> opt = userRepository.findById(id);
        log.info("resRole:::::::" + opt.get());
        User resUser = userMapper.toOptional(opt);
        log.info("resRole:::::::" + resUser);

        if (resUser != null) {
            resUser.setId(id);
            resUser.setName(userDto.getName());
            resUser.setEmail(userDto.getEmail());
            resUser.setBio(userDto.getBio());
            resUser.setDirection(userDto.getDirection());
            resUser.setLastname(userDto.getLastname());
            resUser.setUpdatedAt(LocalDateTime.now());
            resUser.setPhone(userDto.getPhone());
            resUser.setWebsite(userDto.getWebsite());
            resUser.setUsername(userDto.getUsername());
            resUser.setAvatarUrl(userDto.getAvatarUrl());

        } else {
            BusinessRuleException businessRuleException = new BusinessRuleException("0002", "Error validación. Transacion no localizada. ", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        }
        log.info("resRole:::::::" + resUser);
        UserDto save = userMapper.toDto(userRepository.save(resUser));
        return save;
    }

    @Override
    public void deleteUser(Long id) {
        // Implementation here
    }


}
