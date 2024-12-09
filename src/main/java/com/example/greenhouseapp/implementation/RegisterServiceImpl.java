package com.example.greenhouseapp.implementation;

import com.example.greenhouseapp.model.User;
import com.example.greenhouseapp.repository.UserRepository;
import com.example.greenhouseapp.service.RegisterService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegisterServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void registerUser(String name, String password) {
        if (userRepository.findByName(name) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(name, hashedPassword);
        userRepository.save(user);
    }
}