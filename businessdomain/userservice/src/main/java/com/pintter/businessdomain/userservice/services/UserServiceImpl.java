/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.userservice.services;

import com.pintter.businessdomain.userservice.dto.UserDto;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pc
 */
@Service
public class UserServiceImpl implements UserService {

    // Add any required dependencies here (e.g., repositories, mappers)
    
    @Override
    public List<UserDto> getAllUsers() {
        // Implementation here
        return null;
    }

    @Override
    public UserDto getUserById(Long id) {
        // Implementation here
        return null;
    }

    @Override
    public UserDto getCurrentUser(Authentication auth) {
        // Implementation here
        return null;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // Implementation here
        return null;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        // Implementation here
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        // Implementation here
    }
}
