package com.quizbatch.config.util;

import com.quizbatch.tasklets.makequiz.step1apirequest.CategoryTitle;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;


public class MakeQuizJobFlow {


	public Job makeQuizFlow(JobBuilderFactory jobBuilderFactory, Step apiRequestStep,
		Step converterFromResponseStep, Step categoryMapperStep, CategoryTitle categoryTitle) {
		return jobBuilderFactory.get(getJobName(categoryTitle))
			.start(apiRequestStep)
			.on("FAILED").end()
			.from(apiRequestStep)
			.on("*").to(converterFromResponseStep)
			.on("FAILED").end()
			.from(converterFromResponseStep)
			.on("*").to(categoryMapperStep)
			.on("FAILED").end()
			.end()
			.build();
	}

	public String getJobName(CategoryTitle categoryTitle) {
		switch (categoryTitle) {
			case JAVA:
				return "makeJavaQuizJob";
			case DATA_STRUCTURE:
				return "makeDataStructureQuizJob";
			case DATABASE:
				return "makeDatabaseQuizJob";
			case SPRING:
				return "makeSpringQuizJob";
			case NETWORK:
				return "makeNetworkQuizJob";
			case INTERVIEW:
				return "makeInterviewQuizJob";
			default:
				return "emptyCategory";
		}

	}
}
