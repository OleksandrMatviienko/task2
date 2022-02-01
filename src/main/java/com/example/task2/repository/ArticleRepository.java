package com.example.task2.repository;

import com.example.task2.entity.ArticleEntity;
import com.example.task2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {

    List<ArticleEntity> findAllByUserEntity(UserEntity user);

    UUID findByUserEntity(UserEntity userEntity);

}
