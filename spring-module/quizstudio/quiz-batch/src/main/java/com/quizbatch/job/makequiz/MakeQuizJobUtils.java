package com.quizbatch.job.makequiz;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MakeQuizJobUtils {


	@Qualifier("converterFromResponseStep")
	private final Step converterFromResponseStep;

	@Qualifier("categoryMapperStep")
	private final Step categoryMapperStep;


	public Step getCategoryMapperStep() {
		return this.categoryMapperStep;
	}

	public Step getConverterFromResponseStep() {
		return this.converterFromResponseStep;
	}
}
