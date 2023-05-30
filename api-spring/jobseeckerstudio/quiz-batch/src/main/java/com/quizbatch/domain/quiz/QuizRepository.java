package com.quizbatch.domain.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import quiz.domain.quiz.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>, QuizQueryRepository {

}
