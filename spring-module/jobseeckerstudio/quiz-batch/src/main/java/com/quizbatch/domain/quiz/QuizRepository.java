package com.quizbatch.domain.quiz;

import org.springframework.data.jpa.repository.JpaRepository;


public interface QuizRepository extends JpaRepository<Quiz, Long>, QuizQueryRepository {

}
