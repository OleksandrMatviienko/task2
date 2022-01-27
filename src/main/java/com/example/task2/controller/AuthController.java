package com.example.task2.controller;

import com.example.task2.entity.UserEntity;
import com.example.task2.exception.UserAlreadyExistException;
import com.example.task2.service.UserService;
import com.example.task2.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserEntity request) {
        try {
            UserEntity user = userService.saveUser(request);
            if (user != null){
                String token = jwtProvider.generateToken(request.getEmail());
                return ResponseEntity.ok(token);
            }else{
                return ResponseEntity.badRequest().body("Bad Credentials");
            }
        }catch(UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error was occurred");
        }
    }

    @PostMapping("/login")
    public ResponseEntity auth(@RequestBody UserEntity request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getEmail(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getEmail());
        return ResponseEntity.ok(token);
    }
}
