package com.quizbatch.schedulers;

import com.quizbatch.config.BatchConfig;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

	private final BatchConfig batchConfig;
	private final JobLauncher jobLauncher;

	@Scheduled(cron = "0 10/10 19 * * *")
	public void makeQuizJobSchedule() {
		try {
			jobLauncher.run(
				batchConfig.makeQuizJob(),
				new JobParametersBuilder()
					.addString("jobName", "makeQuizJob")
					.addString("datetime", LocalDateTime.now().toString())
					.toJobParameters()  // job parameter 설정
			);
		} catch (JobExecutionException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Scheduled(cron = "0 13/10 19 * * *")
	public void saveQuizAtRDBMSJobSchedule() {
		try {
			jobLauncher.run(
				batchConfig.saveQuizAtRDBMSJob(),
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

	@Scheduled(cron = "0 15/10 19 * * *")
	public void saveQuizAtRedisJobSchedule() {
		try {
			jobLauncher.run(
				batchConfig.saveQuizAtRDBMSJob(),
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
