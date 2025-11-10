package com.example.Online.Quiz.Service;

import com.example.Online.Quiz.Models.Users;
import com.example.Online.Quiz.Repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService;

    @Test
    void testCreateUser() {
        Users user = new Users();
        user.setEmail("test@gmail.com");
        when(usersRepository.save(user)).thenReturn(user);
        assertEquals(user, usersService.createUser(user));
    }

    @Test
    void testFindByEmailAndPassword() {
        Users user = new Users();
        when(usersRepository.findByEmailAndPassword("test@gmail.com", "1234")).thenReturn(user);
        assertNotNull(usersService.findByEmailAndPassword("test@gmail.com", "1234"));
    }
}
