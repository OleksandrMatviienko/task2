package com.example.task2.service;

import com.example.task2.entity.ArticleEntity;
import com.example.task2.entity.UserEntity;
import com.example.task2.exception.ArticleBelongToAnotherUserException;
import com.example.task2.exception.ArticleWasNotFoundException;
import com.example.task2.exception.UserWasNotFoundException;
import com.example.task2.model.ArticleDTO;
import com.example.task2.repository.ArticleRepository;
import com.example.task2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {

        this.articleRepository = articleRepository;

        this.userRepository = userRepository;

    }

    public ArticleDTO createArticle(ArticleEntity articleEntity){

        articleEntity.setUserEntity(getCurrentUser());

        return ArticleDTO.toArticleDTO(articleRepository.save(articleEntity));

    }

    public ArticleDTO getArticlesById(UUID articleId) throws ArticleWasNotFoundException {

        ArticleEntity article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleWasNotFoundException("Article was Not Found with id: " + articleId));

        return ArticleDTO.toArticleDTO(article);

    }

    public List<ArticleDTO> getArticlesForUserWithId(UUID userId) throws UserWasNotFoundException {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserWasNotFoundException("User was Not Found with id: " + userId));

        List<ArticleDTO> articles = new ArrayList<>();

        return articleRepository.findAllByUserEntity(user).stream().map(ArticleDTO :: toArticleDTO).collect(Collectors.toList());

    }

    public UUID deleteArticleById(UUID articleId) throws ArticleBelongToAnotherUserException, ArticleWasNotFoundException {

        UserEntity currentUser = getCurrentUser();

        if (!articleRepository.existsById(articleId)){
            throw new ArticleWasNotFoundException("Article was Not Found with id: " + articleId);
        }

        if (currentUser.getId().equals(articleRepository.findByUserEntity(currentUser))){

            articleRepository.deleteById(articleId);

        }else{
            throw new ArticleBelongToAnotherUserException("Article is belong to another user");
        }

        return articleId;
    }

    private UserEntity getCurrentUser() {

        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findById(principal.getId()).get();

    }
}
