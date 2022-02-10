package com.example.task2.model;

import com.example.task2.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;
    private String username;
    private String email;

    public static UserDTO toUserDTO(UserEntity user){

        UserDTO model = new UserDTO();
        model.setId(user.getId());
        model.setUsername(user.getName());
        model.setEmail(user.getEmail());

        return model;
    }
}
