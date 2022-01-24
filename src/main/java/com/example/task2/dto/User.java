package com.example.task2.dto;

import com.example.task2.entity.UserEntity;

public class User {
    private String email;
    private String name;

    public static User toDTO(UserEntity userEntity){
        User user = new User();
        user.name = userEntity.getName();
        user.email = userEntity.getEmail();
        return user;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
