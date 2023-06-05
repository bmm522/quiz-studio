package com.jobseeckerstudio.user.oauth.cookie;

import com.jobseeckerstudio.user.jwt.JwtToken;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.Cookie;

/**
 * JWT 토큰을 쿠키로 변환하는 유틸리티 클래스입니다.
 */
public enum CookieMaker {

	INSTANCE;

	/**
	 * 주어진 JWT 토큰을 인코딩하여 쿠키로 변환합니다.
	 *
	 * @param jwtToken JWT 토큰
	 * @return JWT 토큰이 포함된 쿠키 객체
	 * @throws UnsupportedEncodingException 인코딩 예외가 발생한 경우
	 */
	public TokenCookie toCookie(JwtToken jwtToken) throws UnsupportedEncodingException {
		String jwt = setEncode(jwtToken.getJwtToken());
		String refresh = setEncode(jwtToken.getRefreshToken());

		return TokenCookie.create(getCookie("Authorization", jwt),
			getCookie("RefreshToken", refresh));

	}

	private String setEncode(String token) throws UnsupportedEncodingException {
		return URLEncoder.encode(token, StandardCharsets.UTF_8);
	}


	private Cookie getCookie(String cookieName, String cookieValue) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		return setCookie(cookie);
	}

	private Cookie setCookie(Cookie cookie) {
		cookie.setPath("/");
		cookie.setMaxAge(60 * 3); // 3분
		cookie.setDomain(".quizstudio.site");
		cookie.isHttpOnly();
		return cookie;
	}
}
