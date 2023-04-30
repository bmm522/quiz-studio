package com.jobseeckerstudio.user.oauth.cookie;

import com.jobseeckerstudio.user.jwt.dto.JwtToken;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public enum CookieMaker {

    INSTANCE;

    public TokenCookie toCookie(JwtToken jwtToken) throws UnsupportedEncodingException {
        String jwt = setEncode(jwtToken.getJwtToken());
        String refresh = setEncode(jwtToken.getRefreshToken());

        return TokenCookie.create(getCookie("Authorization", jwt), getCookie("RefreshToken", refresh));

    }

    private String setEncode(String token) throws UnsupportedEncodingException {
        return URLEncoder.encode(token, "UTF-8");
    }


    private Cookie getCookie(String cookieName, String cookieValue){
        Cookie cookie = new Cookie(cookieName, cookieValue);
        return setCookie(cookie);
    }

    private Cookie setCookie(Cookie cookie){
        cookie.setPath("/");
        cookie.setMaxAge(10); // 3분
        return cookie;
    }
}
