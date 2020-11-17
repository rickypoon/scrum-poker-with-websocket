package com.seekers.scrumpoker.controller;

import com.seekers.scrumpoker.model.User;
import com.seekers.scrumpoker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam(value = "userEmail") String userEmail) {
        User user = userRepository.save(new User(userEmail));
        return "User successfully created with email: " + user.getEmail();
    }
}
