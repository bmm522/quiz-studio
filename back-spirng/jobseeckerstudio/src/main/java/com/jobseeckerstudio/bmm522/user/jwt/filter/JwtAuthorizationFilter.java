package com.jobseeckerstudio.bmm522.user.jwt.filter;

import com.jobseeckerstudio.bmm522.global.exception.UnauthorizedException;
import com.jobseeckerstudio.bmm522.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.bmm522.user.jwt.mapper.JwtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    private final String socialUrl = "/social/login";

    private final JwtMapper mapper = JwtMapper.getJwtMapper();

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        if (requestURI.equals("/api/v1/social/login/google") ) {
            chain.doFilter(request, response);
            System.out.println("여기필터들어옴");
            return;
        }
        System.out.println("1111111111111111");
        JwtToken jwtToken = getJwtToken(request);
        System.out.println("2222222222222");
        System.out.println(jwtToken.getJwtToken());
        System.out.println(jwtToken.getRefreshToken());
        if(jwtToken.checkValidateJwtToken()) {
            chain.doFilter(request, response);
            return;
        }
        System.out.println("33333333333333");
        if(jwtToken.checkValidateRefreshToken()){
            chain.doFilter(request, response);
            return;
        };
        System.out.println("필터탐");
        jwtToken.checkExpiredToken();

        chain.doFilter(request, response);
    }

    private JwtToken getJwtToken(HttpServletRequest request) {
        return  mapper.toJwtTokenOptional(request)
            .orElseThrow(() -> new UnauthorizedException("JwtToken이 null 입니다. "));
    }


}

//|| requestURI.equals("/favicon.ico")
