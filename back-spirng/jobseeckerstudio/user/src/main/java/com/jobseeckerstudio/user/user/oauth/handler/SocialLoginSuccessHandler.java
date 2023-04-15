package com.jobseeckerstudio.user.user.oauth.handler;

import com.jobseeckerstudio.user.user.auth.principal.PrincipalDetails;
import com.jobseeckerstudio.user.user.jwt.JwtTokenFactory;
import com.jobseeckerstudio.user.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.user.jwt.properties.JwtProperties;
import com.jobseeckerstudio.user.user.oauth.cookie.CookieFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class SocialLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenFactory jwtTokenFactory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();

        JwtToken jwtToken = jwtTokenFactory.create(principalDetails.getUser());

        addHeader(response, jwtToken);

        CookieFactory.get().toCookie(response, jwtToken);

        response.sendRedirect("http://localhost:3001/main");
    }

    private void addHeader(HttpServletResponse response, JwtToken jwtToken) {
        response.addHeader(JwtProperties.HEADER_JWT_STRING, jwtToken.getJwtToken());
        response.addHeader(JwtProperties.HEADER_REFRESHTOKEN_STRING, jwtToken.getRefreshToken());
    }
}
