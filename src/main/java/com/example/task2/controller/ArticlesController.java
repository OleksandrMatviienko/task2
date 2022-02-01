package com.example.task2.controller;

import com.example.task2.entity.ArticleEntity;
import com.example.task2.exception.ArticleBelongToAnotherUserException;
import com.example.task2.exception.ArticleWasNotFoundException;
import com.example.task2.payload.ArticleRequest;
import com.example.task2.service.ArticleService;
import com.example.task2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.net.URI;
import java.util.UUID;

import static com.example.task2.util.ValidationConstants.UUID_REGEX;

@RestController
@RequestMapping("/api/article")
@Validated
public class ArticlesController {

    final UserService userService;

    final ArticleService articleService;

    @Autowired
    public ArticlesController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @PostMapping()
    public ResponseEntity createArticle(@Valid @RequestBody ArticleRequest article) {

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setText(article.getArticleText());
        articleEntity.setName(article.getArticleName());

        return ResponseEntity.created(URI.create("/article")).body(articleService.createArticle(articleEntity));
    }

    @GetMapping("/{articleId}")
    public ResponseEntity getArticlesById(@PathVariable @Pattern(regexp = UUID_REGEX, message = "Invalid UUID in id") UUID articleId) throws ArticleWasNotFoundException {

        return ResponseEntity.ok().body(articleService.getArticlesById(articleId));

    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity deleteArticleById(@PathVariable @Pattern(regexp = UUID_REGEX, message = "Invalid UUID in id") UUID articleId) throws ArticleBelongToAnotherUserException, ArticleWasNotFoundException {

        return ResponseEntity.ok().body(articleService.deleteArticleById(articleId));

    }
}