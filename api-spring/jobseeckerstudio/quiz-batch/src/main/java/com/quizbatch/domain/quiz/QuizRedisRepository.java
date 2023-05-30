package com.quizbatch.domain.quiz;

import com.quizbatch.domain.quizschema.QuizSchema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRedisRepository extends CrudRepository<QuizSchema, String> {

}
