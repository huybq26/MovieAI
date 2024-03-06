package com.example.movieAI.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserEntity> loginUser(String emailOrPhone, String password) {
        Optional<UserEntity> userOptional = userRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    @Transactional
    public void registerUser(String name, String email, String phone, String password) {
        if (userRepository.existsByEmailOrPhone(email, phone)) {
            throw new IllegalArgumentException("Email or phone already in use");
        }
        String hashedPassword = passwordEncoder.encode(password);
        UserEntity user = new UserEntity(name, email, phone, hashedPassword);
        userRepository.save(user);
    }
}