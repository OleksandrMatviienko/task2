package com.example.task2.service;

import com.example.task2.entity.UserEntity;
import com.example.task2.exception.UserAlreadyExistException;
import com.example.task2.jwtSecurity.JwtUtils;
import com.example.task2.payload.JwtResponse;
import com.example.task2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    final UserRepository userRepository;

    final JwtUtils jwtUtils;

    final PasswordEncoder encoder;

    final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, JwtUtils jwtUtils, PasswordEncoder encoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }

    public UserEntity registerUser(UserEntity userEntity) throws UserAlreadyExistException {

        if (userRepository.existsByEmail(userEntity.getEmail())) {
            throw new UserAlreadyExistException("User with such Email is already in use!");
        }

        UserEntity user = new UserEntity(userEntity.getName(), userEntity.getEmail(), encoder.encode(userEntity.getPassword()));

        return userRepository.save(user);
    }

    public JwtResponse authenticateUser(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        return new JwtResponse(jwt);
    }
}
