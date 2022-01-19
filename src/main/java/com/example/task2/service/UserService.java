package com.example.task2.service;

import com.example.task2.entity.UserEntity;
import com.example.task2.exception.UserAlreadyExistException;
import com.example.task2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity registerUser (UserEntity user) throws UserAlreadyExistException {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                throw new UserAlreadyExistException("User with this email already exists");
            }
            return userRepository.save(user);
        }
}
