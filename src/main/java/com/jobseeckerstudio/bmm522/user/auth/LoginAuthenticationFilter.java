package com.jobseeckerstudio.bmm522.user.auth;

import com.jobseeckerstudio.bmm522.global.exception.LoginFailException;
import com.jobseeckerstudio.bmm522.user.entity.User;
import com.jobseeckerstudio.bmm522.user.entity.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        User user = UserMapper.toUserFromRequest(request);

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(user);

        Authentication authentication = getAuthentication(authenticationToken);

        PrincipalDetails principalDetails = getPrincipalDetails(authentication);

    }

    private PrincipalDetails getPrincipalDetails(Authentication authentication) {
        try {
            return (PrincipalDetails) authentication.getPrincipal();
        } catch (Exception e){
            throw new LoginFailException("로그인에 실패했습니다. (Principal 객체 만드는과정에서 오류)", e);
        }
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
}
