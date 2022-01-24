package com.example.task2.service;

import com.example.task2.entity.UserEntity;
import com.example.task2.exception.UserAlreadyExistException;
import com.example.task2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity registerUser (UserEntity user) throws UserAlreadyExistException {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                throw new UserAlreadyExistException("User with this email already exists");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }

    @Override
    public UserEntity findByName(String name) {
        UserEntity user = userRepository.findByName(name);
        if (user != null) {
            return user;
        }
        return null;
    }
}
