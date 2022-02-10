package com.example.task2.repository;


import com.example.task2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

}
