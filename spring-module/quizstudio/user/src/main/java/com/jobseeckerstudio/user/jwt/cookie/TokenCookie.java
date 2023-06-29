package com.jobseeckerstudio.user.jwt.cookie;

import javax.servlet.http.Cookie;

public class TokenCookie {

	private final Cookie authorizationCookie;
	private final Cookie refreshTokenCookie;

	private TokenCookie(Cookie authorizationCookie, Cookie refreshTokenCookie) {
		this.authorizationCookie = authorizationCookie;
		this.refreshTokenCookie = refreshTokenCookie;
	}

	public static TokenCookie create(Cookie authorizationCookie, Cookie refreshTokenCookie) {
		return new TokenCookie(authorizationCookie, refreshTokenCookie);
	}

	public Cookie getAuthorizationCookie() {
		return authorizationCookie;
	}

	public Cookie getRefreshTokenCookie() {
		return refreshTokenCookie;
	}
}
