package com.example.amigoscodecourse.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Add business logic here

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void createUser(User newUser) {
        List<User> optionalUser = userRepository.findMyUsersByEmail(newUser.getEmail());
        if (!optionalUser.isEmpty()) throw new IllegalStateException("email taken");
        userRepository.save(newUser);
    }

}
