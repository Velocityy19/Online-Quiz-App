package com.example.Online.Quiz.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.List;

@Entity(name = "quiz_attempts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users user;

    @ManyToOne
    private Quiz quiz;

    private int score;
    private int total;

    private Instant startedAt;
    private Instant finishedAt;

    @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL)
    private List<Answers> answers;
}
