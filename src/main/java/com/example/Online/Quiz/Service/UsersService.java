package com.example.Online.Quiz.Service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.Online.Quiz.Models.Users;
import com.example.Online.Quiz.Repository.UsersRepository;

@Service
public class UsersService {
    
    @Autowired
    UsersRepository usersRepository;

    // Create User
    public void createUser(Users user) {
        usersRepository.save(user);
    }

    //Find user by email and password
    public Users findByEmailAndPassword(String email, String password) {
        return usersRepository.findByEmailAndPassword(email, password);
    }
}
