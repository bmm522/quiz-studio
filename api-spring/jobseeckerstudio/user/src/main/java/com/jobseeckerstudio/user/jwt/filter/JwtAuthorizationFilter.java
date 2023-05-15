package com.jobseeckerstudio.user.jwt.filter;

import com.jobseeckerstudio.user.exception.InvalidTokenException;
import com.jobseeckerstudio.user.exception.UnauthorizedException;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.jwt.mapper.JwtMapper;
import com.jobseeckerstudio.user.service.JwtExpiredChecker;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import lombok.RequiredArgsConstructor;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final String googleUrl = "/api/v1/social/login/google";
    private final String kakaoUrl = "/api/v1/social/login/kakao";

    private final String newToken = "/api/v1/check-expired-jwt";

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            if (shouldSkipFilter(request)) {
                chain.doFilter(request, response);
                return;
            }
            JwtToken jwtToken = JwtMapper.toJwtToken(request);
            jwtToken.checkValidateJwtToken();
            jwtToken.checkValidateRefreshToken();;
            chain.doFilter(request, response);
        } catch (Exception e) {
            handleException(response, e, 401);
        }
    }

    private void handleException(HttpServletResponse response, Exception e, Integer status) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        String errorName = e.getClass().getSimpleName();
        String message = e.getMessage();
        response.getWriter().write(String.format("{\"status\": %d, \"errorName\": \"%s\", \"msg\": \"%s\"}", status, errorName, message));
        response.getWriter().flush();
        response.getWriter().close();
    }

    public boolean shouldSkipFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return googleUrl.equals(requestURI) || kakaoUrl.equals(requestURI) || newToken.equals(requestURI);
    }



}
