package com.jobseeckerstudio.user.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.jobseeckerstudio.user.auth.principal.PrincipalDetails;
import com.jobseeckerstudio.user.auth.principal.mapper.PrincipalMapper;
import com.jobseeckerstudio.user.domain.user.User;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrincipalMapperTest {

	private static final String TEST_USER_KEY = "test_user_key";
	private static final String TEST_SUB = "test_sub";
	private static final String TEST_EMAIL = "test@test.com";

	@Test
	@DisplayName("소셜로그인 시에 , PrincipalDetails 인스턴스 생성")
	void toPrincipalDetailsWithUserInfoTest() {
		User user = User.builder()
			.userKey(TEST_USER_KEY)
			.email(TEST_EMAIL)
			.build();

		Map<String, Object> userInfoMap = new HashMap<>();
		userInfoMap.put("sub", TEST_SUB);
		userInfoMap.put("email", TEST_EMAIL);

		PrincipalDetails principalDetails = PrincipalMapper.toPrincipalDetailsWithUserInfo(user,
			userInfoMap);

		assertThat(user).isEqualTo(principalDetails.getUser());
	}

	@Test
	@DisplayName("로컬로그인 시에 , PrincipalDetails 인스턴스 생성")
	void toPrincipalDetailsWithOutUserInfoTest() {
		User user = User.builder()
			.userKey(TEST_USER_KEY)
			.email(TEST_EMAIL)
			.build();

		PrincipalDetails principalDetails = PrincipalMapper.toPrincipalDetailsWithOutUserInfo(user);

		assertThat(user).isEqualTo(principalDetails.getUser());
	}
}
