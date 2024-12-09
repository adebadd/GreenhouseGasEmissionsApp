package com.example.greenhouseapp.implementation;

import com.example.greenhouseapp.model.User;
import com.example.greenhouseapp.repository.UserRepository;
import com.example.greenhouseapp.service.LoginService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean authenticate(String name, String password) {
        User user = userRepository.findByName(name);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}