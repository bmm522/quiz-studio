package com.jobseeckerstudio.bmm522.user.oauth.handler;

import com.jobseeckerstudio.bmm522.user.auth.principal.PrincipalDetails;
import com.jobseeckerstudio.bmm522.user.auth.principal.mapper.PrincipalMapper;
import com.jobseeckerstudio.bmm522.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.bmm522.user.jwt.properties.JwtProperties;
import com.jobseeckerstudio.bmm522.user.oauth.cookie.CookieFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.jobseeckerstudio.bmm522.user.jwt.JwtTokenFactory.getJwtTokenFactory;

@Component
public class SocialLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();

        JwtToken jwtToken = getJwtTokenFactory().create(principalDetails.getUser());

        addHeader(response, jwtToken);

        CookieFactory.get().toCookie(response, jwtToken);

        response.sendRedirect("http://localhost:3001/menu");
    }

    private void addHeader(HttpServletResponse response, JwtToken jwtToken) {
        response.addHeader(JwtProperties.HEADER_JWT_STRING, jwtToken.getJwtToken());
        response.addHeader(JwtProperties.REFRESH_PREFIX, jwtToken.getRefreshToken());
    }
}
