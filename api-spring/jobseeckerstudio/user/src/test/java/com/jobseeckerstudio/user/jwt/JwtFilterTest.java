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

		when(googleRequest.getRequestURI()).thenReturn("/api/v1/social/login/google");
		when(kakaoRequest.getRequestURI()).thenReturn("/api/v1/social/login/kakao");
		when(newTokenRequest.getRequestURI()).thenReturn("/api/v1/check-expired-jwt");
		when(otherRequest.getRequestURI()).thenReturn("/api/v1/some/other/endpoint");

		Method shouldSkipFilterMethod = JwtAuthorizationFilter.class.getDeclaredMethod(
			"shouldSkipFilter", HttpServletRequest.class);
		shouldSkipFilterMethod.setAccessible(true);

		assertTrue((boolean) shouldSkipFilterMethod.invoke(jwtAuthorizationFilter, googleRequest));
		assertTrue((boolean) shouldSkipFilterMethod.invoke(jwtAuthorizationFilter, kakaoRequest));
		assertTrue(
			(boolean) shouldSkipFilterMethod.invoke(jwtAuthorizationFilter, newTokenRequest));
		assertFalse((boolean) shouldSkipFilterMethod.invoke(jwtAuthorizationFilter, otherRequest));
	}
}
