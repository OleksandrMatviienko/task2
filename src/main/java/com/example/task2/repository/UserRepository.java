package com.example.task2.repository;

import com.example.task2.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    UserEntity findByEmail(String email);
    UserEntity findByName(String name);
}
