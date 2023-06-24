package com.quizbatch.schedulers;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveRedisScheduler {

	private final JobLauncher jobLauncher;

	@Qualifier("saveQuizAtRedis")
	private final Job saveQuizAtRedisJob;

	/**
	 * 매일 5시 30분에 db에 저장된 데이터를 레디스로 저장하는 스케줄링 메서드입니다.
	 */
	@Scheduled(cron = "0 48 2 * * *")
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
