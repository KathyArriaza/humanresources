package com.example.humanresources.services.Implements;

import com.example.humanresources.entities.Role;
import com.example.humanresources.entities.User;
import com.example.humanresources.repositories.RoleRepository;
import com.example.humanresources.repositories.UserRepository;
import com.example.humanresources.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User create(User user) {
        Optional<Role> optionalRole = rolRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        optionalRole.ifPresent(roles::add);

        if(user.isAdmin()) {
            Optional<Role> optionalAdminRole = rolRepository.findByName("ROLE_ADMIN");
            optionalAdminRole.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
