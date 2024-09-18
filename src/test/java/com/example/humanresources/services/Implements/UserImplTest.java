package com.example.humanresources.services.Implements;

import com.example.humanresources.entities.Role;
import com.example.humanresources.entities.User;
import com.example.humanresources.repositories.RoleRepository;
import com.example.humanresources.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserImpl userImpl;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("john.doe");
        user.setPassword("password");
    }

    @Test
    void testFindAll() {
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userImpl.findAll();
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(new Role("ROLE_USER")));
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userImpl.create(user);
        assertNotNull(result);
        assertEquals("encodedPassword", result.getPassword());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testExistsByUsername() {
        when(userRepository.existsByUsername("john.doe")).thenReturn(true);

        boolean result = userImpl.existsByUsername("john.doe");
        assertTrue(result);
        verify(userRepository, times(1)).existsByUsername("john.doe");
    }


}