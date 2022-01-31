package com.example.task2.authcontroller;

import com.example.task2.entity.UserEntity;
import com.example.task2.exception.UserAlreadyExistException;
import com.example.task2.payload.JwtResponse;
import com.example.task2.payload.LoginRequest;
import com.example.task2.payload.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponse Login(@Valid @RequestBody LoginRequest loginRequest) {

        return authService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());

    }

    @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponse registerUserAndLogin(@Valid @RequestBody SignupRequest signUpRequest) throws UserAlreadyExistException {

        UserEntity user = new UserEntity(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());

        authService.registerUser(user);

        return authService.authenticateUser(signUpRequest.getEmail(), signUpRequest.getPassword());
    }



}