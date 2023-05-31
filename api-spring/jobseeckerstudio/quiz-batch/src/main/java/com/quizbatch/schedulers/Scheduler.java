package com.quizbatch.schedulers;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

	private final JobLauncher jobLauncher;
	@Autowired
	@Qualifier("makeJavaQuizJob")
	private Job makeJavaQuizJob;

	@Autowired
	@Qualifier("makeDataStructureQuizJob")
	private Job makeDataStructureQuizJob;

	@Autowired
	@Qualifier("makeDatabaseQuizJob")
	private Job makeDatabaseQuizJob;

	@Autowired
	@Qualifier("saveQuizAtRDBMSJob")
	private Job saveQuizAtRDBMSJob;

	@Autowired
	@Qualifier("saveQuizAtRedisJob")
	private Job saveQuizAtRedisJob;


	@Scheduled(cron = "0 12/5 19 * * *")
	public void makeJavaQuizJobSchedule() {
		try {
			jobLauncher.run(
				makeJavaQuizJob,
				new JobParametersBuilder()
					.addString("jobName", "makeJavaQuizJob")
					.addString("datetime", LocalDateTime.now().toString())
					.toJobParameters()  // job parameter 설정
			);
		} catch (JobExecutionException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Scheduled(cron = "0 0/5 20 * * *")
	public void makeDataStructureQuizJobSchedule() {
		try {
			jobLauncher.run(
				makeDataStructureQuizJob,
				new JobParametersBuilder()
					.addString("jobName", "makeDataStructureQuizJob")
					.addString("datetime", LocalDateTime.now().toString())
					.toJobParameters()  // job parameter 설정
			);
		} catch (JobExecutionException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Scheduled(cron = "0 0/5 21 * * *")
	public void makeDatabaseQuizJobSchedule() {
		try {
			jobLauncher.run(
				makeDatabaseQuizJob,
				new JobParametersBuilder()
					.addString("jobName", "makeDatabaseQuizJob")
					.addString("datetime", LocalDateTime.now().toString())
					.toJobParameters()  // job parameter 설정
			);
		} catch (JobExecutionException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Scheduled(cron = "0 10 22 * * *")
	public void saveQuizAtRDBMSJobSchedule() {
		try {
			jobLauncher.run(
				saveQuizAtRDBMSJob,
				new JobParametersBuilder()
					.addString("jobName", "saveQuizAtRDBMSJob")
					.addString("datetime", LocalDateTime.now().toString())
					.toJobParameters()  // job parameter 설정
			);
		} catch (JobExecutionException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Scheduled(cron = "0 15 22 * * *")
	public void saveQuizAtRedisJobSchedule() {
		try {
			jobLauncher.run(
				saveQuizAtRedisJob,
				new JobParametersBuilder()
					.addString("jobName", "saveQuizAtRedisJob")
					.addString("datetime", LocalDateTime.now().toString())
					.toJobParameters()  // job parameter 설정
			);
		} catch (JobExecutionException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
