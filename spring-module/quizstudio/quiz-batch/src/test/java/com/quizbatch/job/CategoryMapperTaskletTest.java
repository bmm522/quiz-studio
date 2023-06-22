package com.quizbatch.job;


import com.quizbatch.config.BatchConfig;
import com.quizbatch.config.BatchTestConfig;
import javax.batch.runtime.JobExecution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@SpringBatchTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BatchConfig.class, BatchTestConfig.class})
public class CategoryMapperTaskletTest {


	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private Job makeJavaQuizJob;

	@Test
	public void success() throws Exception {
		jobLauncherTestUtils.setJob(makeJavaQuizJob);
		JobExecution jobExecution = (JobExecution) jobLauncherTestUtils.launchStep(
			"categoryMapperStep");

		System.out.println(jobExecution.getExitStatus());
	}
}
