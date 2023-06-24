package com.quizbatch.schedulers;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MakeQuizScheduler {

	private final JobLauncher jobLauncher;
	@Qualifier("makeJavaQuiz")
	private final Job makeJavaQuizJob;


	@Qualifier("makeDataStructureQuizJob")
	private final Job makeDataStructureQuizJob;

	@Qualifier("makeDatabaseQuiz")
	private final Job makeDatabaseQuizJob;


	@Qualifier("makeSpringQuiz")
	private final Job makeSpringQuizJob;


	@Qualifier("makeNetworkQuiz")
	private final Job makeNetworkQuizJob;


	@Qualifier("makeInterviewQuiz")
	private final Job makeInterviewQuizJob;

	/**
	 * 매일 오후 10시부터 25분마다 자바 문제를 생성하는 스케줄링 메서드입니다.
	 */
	@Scheduled(cron = "0 1/25 13 * * *")
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
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * 매일 오후 11시부터 25분마다 자료구조 문제를 생성하는 스케줄링 메서드입니다.
	 */
	@Scheduled(cron = "0 1/25 14 * * *")
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
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * 매일 오후 12시부터 25분마다 데이터 베이스 문제를 생성하는 스케줄링 메서드입니다.
	 */
	@Scheduled(cron = "0 1/25 15 * * *")
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
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * 매일 오전  1시부터 15분마다 스프링 프레임워크 문제를 생성하는 스케줄링 메서드입니다.
	 */
	@Scheduled(cron = "0 1/15 16 * * *")
	public void makeSpringQuizJobSchedule() {
		try {
			jobLauncher.run(
				makeSpringQuizJob,
				new JobParametersBuilder()
					.addString("jobName", "makeSpringQuizJob")
					.addString("datetime", LocalDateTime.now().toString())
					.toJobParameters()  // job parameter 설정
			);
		} catch (JobExecutionException ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * 매일 오후전 2시부터 15분마다 네트워크 문제를 생성하는 스케줄링 메서드입니다.
	 */
	@Scheduled(cron = "0 1/15 17 * * *")
	public void makeNetworkQuizJobSchedule() {
		try {
			jobLauncher.run(
				makeNetworkQuizJob,
				new JobParametersBuilder()
					.addString("jobName", "makeNetworkQuizJob")
					.addString("datetime", LocalDateTime.now().toString())
					.toJobParameters()  // job parameter 설정
			);
		} catch (JobExecutionException ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * 매일 오전 3시부터 15분마다 cs 면접 문제를 생성하는 스케줄링 메서드입니다.
	 */
	@Scheduled(cron = "0 1/15 18 * * *")
	public void makeInterviewQuizJobSchedule() {
		try {
			jobLauncher.run(
				makeInterviewQuizJob,
				new JobParametersBuilder()
					.addString("jobName", "makeInterviewQuizJob")
					.addString("datetime", LocalDateTime.now().toString())
					.toJobParameters()  // job parameter 설정
			);
		} catch (JobExecutionException ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
	}


}
