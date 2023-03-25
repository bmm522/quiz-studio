package com.jobseeckerstudio.bmm522;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JobSeeckerStudioApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSeeckerStudioApplication.class, args);
	}

}
