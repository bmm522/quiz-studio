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



    private final JwtMapper mapper = JwtMapper.getJwtMapper();

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

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

        request.setAttribute("jwtToken", jwtToken);
        chain.doFilter(request, response);
    }

    private JwtToken getJwtToken(HttpServletRequest request) {
        return  mapper.toJwtToken(request)
            .orElseThrow(() -> new UnauthorizedException("JwtToken이 null 입니다. "));
    }


}
