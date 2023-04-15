package com.jobseeckerstudio.bmm522.quiz.entity.quiz.repository;

import com.jobseeckerstudio.bmm522.quiz.entity.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
