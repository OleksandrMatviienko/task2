package com.example.task2.controller;

import com.example.task2.exception.UserWasNotFoundException;
import com.example.task2.model.UserDTO;
import com.example.task2.service.ArticleService;
import com.example.task2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

import static com.example.task2.util.ValidationConstants.UUID_REGEX;

@RestController
@RequestMapping("/api/user")
public class UserController {

    final UserService userService;

    final ArticleService articleService;

    @Autowired
    public UserController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        return ResponseEntity.ok().body(userService.findAll());

    }

    @GetMapping("/{userId}/article")
    public ResponseEntity getArticlesForUserWithId(@Valid @PathVariable @Pattern(regexp = UUID_REGEX, message = "Invalid UUID in id") UUID userId) throws UserWasNotFoundException {

        return ResponseEntity.ok().body(articleService.getArticlesForUserWithId(userId));

    }
}
