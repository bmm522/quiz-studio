package com.jobseeckerstudio.user.auth.filter;

import com.jobseeckerstudio.user.auth.principal.PrincipalDetails;
import com.jobseeckerstudio.user.exception.LoginFailException;
import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.domain.user.mapper.UserMapper;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        User user = UserMapper.toUserFromRequest(request);

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(user);

        return getAuthentication(authenticationToken);

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = getPrincipalDetails(authResult);

        JwtToken jwtToken = JwtMaker.create(principalDetails.getUser());

        response.addHeader(JwtProperties.HEADER_JWT_STRING, jwtToken.getJwtToken());
        response.addHeader(JwtProperties.REFRESH_PREFIX, jwtToken.getRefreshToken());

    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(User user) {
        return new UsernamePasswordAuthenticationToken(user.getUserKey(), user.getPassword());
    }

    private Authentication getAuthentication(UsernamePasswordAuthenticationToken authenticationToken) {
        try {
            return authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            throw new LoginFailException("등록되지 않은 회원입니다.", e);
        }
    }

    private PrincipalDetails getPrincipalDetails(Authentication authentication) {
        try {
            return (PrincipalDetails) authentication.getPrincipal();
        } catch (Exception e){
            throw new LoginFailException("로그인에 실패했습니다. (Principal 객체 만드는과정에서 오류)", e);
        }
    }
}
