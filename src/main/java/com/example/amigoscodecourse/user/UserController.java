package com.example.amigoscodecourse.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoints here

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public Object createUser(@RequestBody User newUser) {
        try {
            userService.createUser(newUser);
            return Map.of("message", "User successfully created");
        } catch (Error error) {
            return error;
        }

    }

}
