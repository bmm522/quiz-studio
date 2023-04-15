package com.jobseeckerstudio.user.quiz.entity.quiz.repository;

import com.jobseeckerstudio.user.quiz.entity.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
