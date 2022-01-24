package com.example.task2.controller;

import com.example.task2.entity.UserEntity;
import com.example.task2.exception.UserAlreadyExistException;
import com.example.task2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity loginUser (@RequestBody UserEntity user) {
        try {
            //userService.loginUser(user);
            return ResponseEntity.ok("User was logged");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error was occurred");
        }
    }

    @GetMapping("/login")
    public ResponseEntity loginUser () {
        try {
            //userService.loginUser(user);
            return ResponseEntity.ok("User was logged");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error was occurred");
        }
    }

    @PostMapping("/register")
    public ResponseEntity registerUser (@RequestBody UserEntity user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("User was saved");
        }catch (UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error was occurred");
        }
    }
}
