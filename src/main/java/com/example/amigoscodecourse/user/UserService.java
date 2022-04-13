package com.example.amigoscodecourse.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Transactional
    public void putUser(Long userId, String name, String email) {
        User resultUser = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new IllegalStateException("no user with this email");
                });
        if ( name != null && name.length() > 0 && !Objects.equals(name, resultUser.getName())) resultUser.setName(name);
        if ( email != null && email.length() > 0 && !Objects.equals(email, resultUser.getEmail())) {
            List<User> optionalUsers = userRepository.findMyUsersByEmail(email);
            if(!optionalUsers.isEmpty()) throw new IllegalStateException("email taken");
            resultUser.setEmail(email);
        }
    }

}
