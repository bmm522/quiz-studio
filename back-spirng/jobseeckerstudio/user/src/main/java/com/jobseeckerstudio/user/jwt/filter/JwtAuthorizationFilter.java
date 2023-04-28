package com.jobseeckerstudio.user.jwt.filter;

import com.jobseeckerstudio.user.exception.UnauthorizedException;
import com.jobseeckerstudio.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.jwt.mapper.JwtMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    private final String googleUrl = "/api/v1/social/login/google";
    private final String kakaoUrl = "/api/v1/social/login/kakao";

    private final String newToken = "/api/v1/new-token";

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();

        if (googleUrl.equals(requestURI) || kakaoUrl.equals(requestURI) || newToken.equals(requestURI)  ) {
            chain.doFilter(request, response);
            return;
        }

        JwtToken jwtToken = getJwtToken(request);

        if(jwtToken.checkValidateJwtToken()) {
            chain.doFilter(request, response);
            return;
        }

        if(jwtToken.checkValidateRefreshToken()){
            chain.doFilter(request, response);
            return;
        };

        jwtToken.checkExpiredToken();

        chain.doFilter(request, response);
    }

    private JwtToken getJwtToken(HttpServletRequest request) {
        return  JwtMapper.toJwtTokenOptional(request)
            .orElseThrow(() -> new UnauthorizedException("JwtToken이 null 입니다. "));
    }


}

//|| requestURI.equals("/favicon.ico")
