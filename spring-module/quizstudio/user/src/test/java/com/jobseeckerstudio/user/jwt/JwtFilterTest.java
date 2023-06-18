package com.jobseeckerstudio.user.jwt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.jobseeckerstudio.user.jwt.filter.JwtAuthorizationFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-dev.yml")
public class JwtFilterTest {

	private JwtAuthorizationFilter jwtAuthorizationFilter;


	@BeforeEach
	void setUp() {
		AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
		jwtAuthorizationFilter = new JwtAuthorizationFilter(authenticationManager);
	}

	@Test
	@DisplayName("필터를 건너뛰어야 하는 경우 테스트")
	void shouldSkipFilterTest()
		throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		HttpServletRequest googleRequest = mock(HttpServletRequest.class);
		HttpServletRequest kakaoRequest = mock(HttpServletRequest.class);
		HttpServletRequest newTokenRequest = mock(HttpServletRequest.class);
		HttpServletRequest otherRequest = mock(HttpServletRequest.class);

		when(googleRequest.getRequestURI()).thenReturn("/user/api/v1/social/login/google");
		when(kakaoRequest.getRequestURI()).thenReturn("/user/api/v1/social/login/kakao");
		when(newTokenRequest.getRequestURI()).thenReturn("/user/api/v1/check-expired-jwt");
		when(otherRequest.getRequestURI()).thenReturn("/user/api/v1/some/other/endpoint");

		Method shouldSkipFilterWhenSocilaLoginMethod = JwtAuthorizationFilter.class.getDeclaredMethod(
			"shouldSkipFilterWhenSocilaLogin", HttpServletRequest.class);
		shouldSkipFilterWhenSocilaLoginMethod.setAccessible(true);

		Method shouldSkipFilterWhenGuestLoginMethod = JwtAuthorizationFilter.class.getDeclaredMethod(
			"shouldSkipFilterWhenGuestLogin", HttpServletRequest.class);
		shouldSkipFilterWhenGuestLoginMethod.setAccessible(true);

		assertTrue((boolean) shouldSkipFilterWhenSocilaLoginMethod.invoke(jwtAuthorizationFilter,
			googleRequest));
		assertTrue((boolean) shouldSkipFilterWhenSocilaLoginMethod.invoke(jwtAuthorizationFilter,
			kakaoRequest));
		assertTrue(
			(boolean) shouldSkipFilterWhenSocilaLoginMethod.invoke(jwtAuthorizationFilter,
				newTokenRequest));
		assertTrue(
			(boolean) shouldSkipFilterWhenGuestLoginMethod.invoke(jwtAuthorizationFilter, "guest"));
		assertFalse((boolean) shouldSkipFilterWhenSocilaLoginMethod.invoke(jwtAuthorizationFilter,
			otherRequest));
	}
}
