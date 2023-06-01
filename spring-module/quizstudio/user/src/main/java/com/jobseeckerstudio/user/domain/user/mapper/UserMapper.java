package com.jobseeckerstudio.user.domain.user.mapper;

import com.jobseeckerstudio.user.domain.Status;
import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.oauth.info.Provider;
import com.jobseeckerstudio.user.oauth.info.SocialUserInfo;
import javax.servlet.http.HttpServletRequest;

public class UserMapper {

	/**
	 * HTTP 요청에서 User 객체를 생성하는 메서드입니다.
	 *
	 * @param request HTTP 요청 객체
	 * @return 생성된 User 객체
	 */
	public static User toUserFromRequest(final HttpServletRequest request) {
		return User.builder()
			.userKey((String) request.getAttribute("userKey"))
			.password("null")
			.build();
	}

	/**
	 * SocialUserInfo를 이용하여 User 객체를 생성하는 메서드입니다.
	 *
	 * @param socialUserInfo SocialUserInfo 객체
	 * @return 생성된 User 객체
	 */
	public static User toUserFromSocialInfo(final SocialUserInfo socialUserInfo) {
		return User.builder()
			.userKey(Provider.Google.getProvider() + "_" + socialUserInfo.getUserKey())
			.password("null")
			.email(socialUserInfo.getEmail())
			.status(Status.Y)
			.build();
	}
}
