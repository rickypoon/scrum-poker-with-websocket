package com.seekers.scrumpoker.controller;

import com.seekers.scrumpoker.model.User;
import com.seekers.scrumpoker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody Map<String, String> params) {
        User user = userRepository.save(new User(params.get("userEmail")));
        return "User successfully created with email: " + user.getEmail();
    }
}
