package com.jobseeckerstudio.bmm522.user.entity.quiz.repository;

import com.jobseeckerstudio.bmm522.user.entity.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
