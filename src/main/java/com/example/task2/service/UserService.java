package com.example.task2.service;

import com.example.task2.entity.UserEntity;
import com.example.task2.exception.UserAlreadyExistException;

public interface UserService {
    UserEntity registerUser(UserEntity user) throws UserAlreadyExistException;
    UserEntity findByName(String name);
}
