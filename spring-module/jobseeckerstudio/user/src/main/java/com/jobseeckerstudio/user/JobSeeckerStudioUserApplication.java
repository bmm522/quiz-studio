package com.jobseeckerstudio.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JobSeeckerStudioUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSeeckerStudioUserApplication.class, args);
	}

}
