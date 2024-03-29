package com.jobseeckerstudio.user.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.domain.user.mapper.UserMapper;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

	private final String userKey = "test_user";
	@Autowired
	private HttpServletRequest request;

	@Test
	@DisplayName("toUserFromRequest 테스트 (소셜로그인 시에 필요)")
	void toUserFromRequestTest() {
		request.setAttribute("userKey", userKey);

		User user = UserMapper.toUserFromRequest(request);

		assertThat(user.getUserKey()).isEqualTo(userKey);
	}
}
