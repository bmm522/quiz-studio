package com.quizbatch.tasklets.makequiz.step1apirequest;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiDatabaseRequestTasklet extends ApiRequestAbstractTemplate {


	public ApiDatabaseRequestTasklet(RestTemplate restTemplate) {
		super(restTemplate);
	}

	@Override
	public CategoryTitle getCategoryTitle() {
		return CategoryTitle.DATABASE;
	}
}
