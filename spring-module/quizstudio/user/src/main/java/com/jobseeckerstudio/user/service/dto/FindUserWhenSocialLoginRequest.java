package com.jobseeckerstudio.user.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FindUserWhenSocialLoginRequest {

	private final String userKey;

	@Builder
	public FindUserWhenSocialLoginRequest(final String userKey) {
		this.userKey = userKey;
	}
}
