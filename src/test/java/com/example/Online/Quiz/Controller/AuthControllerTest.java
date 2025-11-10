package com.example.Online.Quiz.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.Online.Quiz.Models.Users;
import com.example.Online.Quiz.Service.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private UsersService usersService;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private AuthController authController;

    @Test
    void testShowLoginForm() {
        String result = authController.showLoginForm(model);
        assertEquals("login", result);
    }

    @Test
    void testRegisterUser() {
        Users user = new Users();
        user.setEmail("test@gmail.com");
        user.setPassword("12345");

        String result = authController.registerUser(user);
        assertEquals("redirect:/login", result);
    }

    @Test
    void testLoginUser_Success() {
        Users user = new Users();
        user.setEmail("user@gmail.com");
        user.setPassword("123");

        when(usersService.findByEmailAndPassword("user@gmail.com", "123")).thenReturn(user);

        String result = authController.loginUser(user, session, model);
        assertEquals("redirect:/participant/quizzes", result);
    }
}
