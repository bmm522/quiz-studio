//package com.quizbatch.job;
//
//
//import com.quizbatch.config.BatchTestConfig;
//import com.quizbatch.job.saverdbms.SaveQuizAtRDBMS;
//import com.quizbatch.tasklets.saverdbms.step1save.SaveQuizAtRDBMSTasklet;
//import org.junit.jupiter.api.Test;
//import org.springframework.batch.test.JobLauncherTestUtils;
//import org.springframework.batch.test.context.SpringBatchTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//@SpringBootTest(classes = {BatchTestConfig.class, SaveQuizAtRDBMS.class,
//	SaveQuizAtRDBMSTasklet.class, BatchConfig.class})
//@SpringBatchTest
//public class SaveQuizAtRDBMSTest {
//
//
//	@Autowired
//	private JobLauncherTestUtils jobLauncherTestUtils;
//
////	@Autowired
////	private Job makeJavaQuizJob;
//
//	@Test
//	public void success() throws Exception {
////
////		JobExecution jobExecution = (JobExecution) jobLauncherTestUtils.launchJob();
////
////		System.out.println(jobExecution.getExitStatus());
//	}
//}
