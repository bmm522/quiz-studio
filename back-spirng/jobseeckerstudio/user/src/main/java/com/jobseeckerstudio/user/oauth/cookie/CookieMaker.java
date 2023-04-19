package com.jobseeckerstudio.user.oauth.cookie;

import com.jobseeckerstudio.user.jwt.dto.JwtToken;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
public class CookieMaker {


    public static final CookieMaker instance = new CookieMaker();
//    private static CookieMaker cookieMaker = new CookieMaker();
//
//    public static CookieMaker get() {return cookieMaker;}

    private CookieMaker(){}
    public void toCookie(HttpServletResponse response, JwtToken jwtToken) throws UnsupportedEncodingException {
        String jwt = setEncode(jwtToken.getJwtToken());
        String refresh = setEncode(jwtToken.getRefreshToken());

        response.addCookie(getCookie("Authorization", jwt));
        response.addCookie(getCookie("RefreshToken", refresh));
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
        cookie.setMaxAge(60*3); // 3분
        return cookie;
    }
}
