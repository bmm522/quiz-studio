package com.jobseeckerstudio.user.service;

import com.jobseeckerstudio.user.service.dto.CheckLoginResponse;
import javax.servlet.http.Cookie;
import org.springframework.stereotype.Service;

@Service
public class CheckLoginService {


	private final String accessTokenCookieName = "Authorization";

	private final String refreshTokenCookieName = "RefreshToken";

	
	public CheckLoginResponse checkLogin(Cookie[] cookies) {
		int checkCnt = 0;

		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if (accessTokenCookieName.equals(cookieName)) {
				checkCnt++;
			}

			if (refreshTokenCookieName.equals(cookieName)) {
				checkCnt++;
			}
		}

		if (checkCnt == 2) {
			return new CheckLoginResponse("loginUser");
		}
		return new CheckLoginResponse("guest");
	}
}
