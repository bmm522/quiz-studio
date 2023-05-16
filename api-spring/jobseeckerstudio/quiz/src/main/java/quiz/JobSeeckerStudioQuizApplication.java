package quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import quiz.application.Runner;

@SpringBootApplication
@EnableJpaAuditing
public class JobSeeckerStudioQuizApplication {


	public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(JobSeeckerStudioQuizApplication.class, args);
        Runner runner = context.getBean(Runner.class);
        runner.run(args);
	}

}
