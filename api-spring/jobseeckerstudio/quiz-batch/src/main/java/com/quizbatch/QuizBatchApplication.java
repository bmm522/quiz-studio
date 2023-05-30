package com.quizbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableBatchProcessing
@EntityScan(basePackages = {"quiz.domain"})
@EnableJpaRepositories
@SpringBootApplication
public class QuizBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizBatchApplication.class, args);
	}

}
