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

	/**
	 * 매일 1시부터 10분마다 자바 문제를 생성하는 스케줄링 메서드입니다.
	 */
	@Scheduled(cron = "0 0/10 1 * * *")
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

	/**
	 * 매일 2시부터 10분마다 자료구조 문제를 생성하는 스케줄링 메서드입니다.
	 */
	@Scheduled(cron = "0 1/10 2 * * *")
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

	/**
	 * 매일 3시부터 10분마다 데이터 베이스 문제를 생성하는 스케줄링 메서드입니다.
	 */
	@Scheduled(cron = "0 1/10 3 * * *")
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

	/**
	 * 매일 5시에 Queue에 담긴 데이터를 db에 저장하는 스케줄링 메서드입니다.
	 */
	@Scheduled(cron = "0 0 5 * * *")
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

	/**
	 * 매일 5시 30분에 db에 저장된 데이터를 레디스로 저장하는 스케줄링 메서드입니다.
	 */
	@Scheduled(cron = "0 30 5 * * *")
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