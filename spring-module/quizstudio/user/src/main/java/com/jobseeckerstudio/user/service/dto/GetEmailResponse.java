package com.jobseeckerstudio.user.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetEmailResponse {

	private final String email;

	@Builder
	public GetEmailResponse(final String email) {
		this.email = email;
	}
}
