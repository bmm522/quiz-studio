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

		return TokenCookie.create(getCookie("Authorization", jwt, 5),
			getCookie("RefreshToken", refresh, 5));

	}

	public TokenCookie toCookieWhenLogout() {
		return TokenCookie.create(getCookie("Authorization", "", 0),
			getCookie("RefreshToken", "", 0));
	}

	public String setEncode(String token) throws UnsupportedEncodingException {
		return URLEncoder.encode(token, StandardCharsets.UTF_8);
	}


	public Cookie getCookie(String cookieName, String cookieValue, int expiredTime) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setPath("/");
		cookie.setMaxAge(expiredTime);
		cookie.setDomain(".quizstudio.site");
		cookie.setSecure(true);
		return cookie;
	}

}
