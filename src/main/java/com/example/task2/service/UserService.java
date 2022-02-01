package com.example.task2.service;

import com.example.task2.entity.UserEntity;
import com.example.task2.exception.UserWasNotFoundException;
import com.example.task2.model.UserDTO;
import com.example.task2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User was Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::toUserDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(UUID id) throws UserWasNotFoundException {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserWasNotFoundException("User was Not Found with id: " + id));
        return UserDTO.toUserDTO(user);
    }

}
