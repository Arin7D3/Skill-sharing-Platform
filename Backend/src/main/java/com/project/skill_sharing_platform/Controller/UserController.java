package com.project.skill_sharing_platform.Controller;

import com.project.skill_sharing_platform.Service.UserService;
import com.project.skill_sharing_platform.Entity.User;
import com.project.skill_sharing_platform.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDto) {
        return userService.register(userDto);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody UserDTO userDto) {
        return userService.updateUser(id, userDto);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

