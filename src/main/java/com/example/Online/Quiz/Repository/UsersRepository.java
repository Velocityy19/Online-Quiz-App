package com.example.Online.Quiz.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online.Quiz.Models.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmailAndPassword(String email, String password);
    
}
