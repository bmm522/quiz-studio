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
public class SaveRDBMSScheduler {

	private final JobLauncher jobLauncher;

	@Qualifier("saveQuizAtRDBMSJob")
	private final Job saveQuizAtRDBMSJob;

	/**
	 * 매일 5시에 Queue에 담긴 데이터를 db에 저장하는 스케줄링 메서드입니다.
	 */
	@Scheduled(cron = "0 0 20 * * *")
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
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
