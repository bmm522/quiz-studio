package com.jobseeckerstudio.user.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.jobseeckerstudio.user.service.dto.CheckLoginResponse;
import javax.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckLoginServiceTest {

	CheckLoginService checkLoginService;

	@BeforeEach
	void init() {
		checkLoginService = new CheckLoginService();
	}

	@Test
	void 토큰_있을때_loginUser_반환() {

		Cookie cookie1 = new Cookie("Authorization", " ");
		Cookie cookie2 = new Cookie("RefreshToken", " ");

		Cookie[] cookies = new Cookie[]{cookie1, cookie2};

		CheckLoginResponse response = checkLoginService.checkLogin(cookies);

		assertThat(response.getResult()).isEqualTo("loginUser");
	}

	@Test
	void 토큰_없을때_guest_반환() {

		Cookie cookie1 = new Cookie("a", " ");
		Cookie cookie2 = new Cookie("b", " ");

		Cookie[] cookies = new Cookie[]{cookie1, cookie2};

		CheckLoginResponse response = checkLoginService.checkLogin(cookies);

		assertThat(response.getResult()).isEqualTo("guest");
	}

	@Test
	void 빈_쿠키배열_요청_들어왔을때_guest_반환() {

		Cookie[] cookies = new Cookie[0];

		CheckLoginResponse response = checkLoginService.checkLogin(cookies);

		assertThat(response.getResult()).isEqualTo("guest");
	}
}
