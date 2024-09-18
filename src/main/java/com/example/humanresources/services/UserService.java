package com.example.humanresources.services;

import com.example.humanresources.entities.User;

import java.util.List;


public interface UserService {
    List<User> findAll();

    User create(User user);

    boolean existsByUsername(String username);
}
