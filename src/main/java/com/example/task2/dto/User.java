package com.example.task2.dto;

import com.example.task2.entity.UserEntity;
import lombok.Data;

@Data
public class User {
    private String email;
    private String username;

    public static User toDTO(UserEntity userEntity){
        User user = new User();
        user.username = userEntity.getName();
        user.email = userEntity.getEmail();
        return user;
    }

}
