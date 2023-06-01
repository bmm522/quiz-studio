package com.jobseeckerstudio.user.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.exception.InvalidTokenException;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.oauth.info.GoogleUserInfo;
import com.jobseeckerstudio.user.oauth.info.KakaoUserInfo;
import com.jobseeckerstudio.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.user.service.dto.FindUserWhenSocialLoginRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailResponse;
import com.jobseeckerstudio.user.service.mapper.UserServiceMapper;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-dev.yml")
public class UserServiceMapperTest {

	private static final String TEST_SUB = "test_sub";
	private static final String TEST_EMAIL = "test@test.com";

	private static final int EXPIRATION_TIME = 1000 * 60 * 60;

	private static final int REFRESHTOKEN_EXPIRATION_TIME = 14 * 24 * 6 * 10 * 60000;
	@Mock
	SocialUserInfo socialUserInfoMock;

	@Test
	@DisplayName("(Google 일 경우)SocialUserInfo 객체로 FindUserWhenSocialLoginRequest 객체 변경 ")
	void toFindUserWhenSocialLoginRequestTestWhenGoogle() {
		Map<String, Object> userInfoMap = new HashMap<>();
		userInfoMap.put("sub", TEST_SUB);
		userInfoMap.put("email", TEST_EMAIL);
		SocialUserInfo googleUserInfo = new GoogleUserInfo(userInfoMap);

		FindUserWhenSocialLoginRequest request = UserServiceMapper.toFindUserWhenSocialLoginRequest(
			googleUserInfo);

		assertThat(request.getUserKey()).isEqualTo("Google_" + TEST_SUB);
	}

	@Test
	@DisplayName("(Kakao 일 경우)SocialUserInfo 객체로 FindUserWhenSocialLoginRequest 객체 변경 ")
	void toFindUserWhenSocialLoginRequestTest() {
		Map<String, Object> userInfoMap = new HashMap<>();
		userInfoMap.put("id", TEST_SUB);
		Map<String, Object> kakaoAccountMap = new HashMap<>();
		kakaoAccountMap.put("email", TEST_EMAIL);
		userInfoMap.put("kakao_account", kakaoAccountMap);

		KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(userInfoMap);

		FindUserWhenSocialLoginRequest request = UserServiceMapper.toFindUserWhenSocialLoginRequest(
			kakaoUserInfo);

		assertThat(request.getUserKey()).isEqualTo("Kakao_" + TEST_SUB);
	}

	@Test
	@DisplayName("email 문자열로 GetEmailResponse 객체 생성")
	void toGetEmailResponseTest() {
		GetEmailResponse getEmailResponse = UserServiceMapper.toGetEmailResponse(TEST_EMAIL);

		assertThat(getEmailResponse.getEmail()).isEqualTo(TEST_EMAIL);
	}

	@Test
	@DisplayName("JwtToken 객체로 GetEmailRequest 객체 생성")
	void toGetEmailRequestTest() {
		User user = User.builder().userKey("test_user").build();
		JwtToken jwtToken = JwtMaker.create(user);
		GetEmailRequest getEmailRequest = UserServiceMapper.toGetEmailRequest(jwtToken);

		assertThat(getEmailRequest.getUserKey()).isEqualTo(user.getUserKey());
	}

	@Test
	@DisplayName("잘못된 JwtToken 객체로 GetEmailRequest 객체 생성 요청할 때")
	void toGetEmailRequestTestWhenInvalidToken() {
		User user = User.builder().userKey("test_user").build();
		JwtToken jwtToken = new JwtToken("test", "test");

		Assertions.assertThrows(InvalidTokenException.class,
			() -> UserServiceMapper.toGetEmailRequest(jwtToken));
	}


}
