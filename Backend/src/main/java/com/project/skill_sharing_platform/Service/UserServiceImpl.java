package com.project.skill_sharing_platform.Service;

import com.project.skill_sharing_platform.Entity.User;
import com.project.skill_sharing_platform.dto.UserDTO;

import lombok.Data;

import com.project.skill_sharing_platform.Repository.UserRepository;
import com.project.skill_sharing_platform.Exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service


public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDTO userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) throw new UserNotFoundException("User not found");
        userRepository.deleteById(id);
    }
}
 {
    
}
