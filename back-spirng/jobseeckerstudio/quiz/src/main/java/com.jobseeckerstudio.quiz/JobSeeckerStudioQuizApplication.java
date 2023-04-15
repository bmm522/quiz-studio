package com.jobseeckerstudio.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JobSeeckerStudioQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSeeckerStudioQuizApplication.class, args);
	}

}
