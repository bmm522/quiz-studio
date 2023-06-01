package com.jobseeckerstudio.user.service.mapper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jobseeckerstudio.user.exception.InvalidTokenException;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import com.jobseeckerstudio.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.user.service.dto.FindUserWhenSocialLoginRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailResponse;

public class UserServiceMapper {

	private static final String userKey = "userKey";

	/**
	 * 소셜 로그인 정보를 이용하여 FindUserWhenSocialLoginRequest 객체로 변환합니다.
	 *
	 * @param socialUserInfo 소셜 로그인 정보
	 * @return FindUserWhenSocialLoginRequest 객체
	 */
	public static FindUserWhenSocialLoginRequest toFindUserWhenSocialLoginRequest(
		final SocialUserInfo socialUserInfo) {
		return FindUserWhenSocialLoginRequest.builder()
			.userKey(socialUserInfo.getProvider() + "_" + socialUserInfo.getUserKey())
			.build();
	}

	/**
	 * JWT 토큰을 이용하여 GetEmailRequest 객체로 변환합니다.
	 *
	 * @param jwtToken JWT 토큰
	 * @return GetEmailRequest 객체
	 */
	public static GetEmailRequest toGetEmailRequest(final JwtToken jwtToken) {
		return GetEmailRequest.builder()
			.userKey(
				parsingJwt(jwtToken.getJwtToken().replace(JwtProperties.TOKEN_PREFIX, ""), userKey))
			.build();
	}

	/**
	 * 이메일 정보를 이용하여 GetEmailResponse 객체로 변환합니다.
	 *
	 * @param email 이메일 정보
	 * @return GetEmailResponse 객체
	 */
	public static GetEmailResponse toGetEmailResponse(final String email) {
		return GetEmailResponse.builder()
			.email(email)
			.build();
	}


	private static String parsingJwt(final String jwtToken, final String key) {
		try {
			return JWT.require(Algorithm.HMAC256(JwtProperties.SECRET)).build().verify(jwtToken)
				.getClaim(key).asString();
		} catch (Exception e) {
			throw new InvalidTokenException("잘못된 토큰 정보입니다.");
		}

	}


}
