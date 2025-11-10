package com.example.Online.Quiz.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online.Quiz.Models.Options;

public interface OptionsRepository extends JpaRepository<Options, Long>     {
    
}
