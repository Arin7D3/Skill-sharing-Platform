package com.project.skill_sharing_platform.Service;

import com.project.skill_sharing_platform.Entity.User;
import com.project.skill_sharing_platform.dto.UserDTO;

import java.util.List;

public interface UserService {
    User register(UserDTO userDto);
    User updateUser(Long id, UserDTO userDto);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
}

