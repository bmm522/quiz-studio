package com.jobseeckerstudio.user.oauth.handler;

import com.jobseeckerstudio.user.auth.principal.PrincipalDetails;
import com.jobseeckerstudio.user.domain.user.User;


import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.oauth.cookie.CookieMaker;
import com.jobseeckerstudio.user.oauth.cookie.TokenCookie;
import com.jobseeckerstudio.user.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class SocialLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();

        User user = principalDetails.getUser();
        JwtToken jwtToken = JwtMaker.create(user);
        settingUserAndGetRefreshToken(user, jwtToken);
//        addHeader(response, jwtToken);
        TokenCookie tokenCookie = CookieMaker.INSTANCE.toCookie(jwtToken);

        addCookie(response, tokenCookie);

        response.sendRedirect("http://localhost:3001/main");
    }


    private void addCookie(HttpServletResponse response, TokenCookie tokenCookie) {
        response.addCookie(tokenCookie.getAuthorizationCookie());
        response.addCookie(tokenCookie.getRefreshTokenCookie());
    }
//    private void addHeader(HttpServletResponse response, JwtToken jwtToken) {
//        response.addHeader(JwtProperties.HEADER_JWT_STRING, jwtToken.getJwtToken());
//        response.addHeader(JwtProperties.HEADER_REFRESHTOKEN_STRING, jwtToken.getRefreshToken());
//    }

    private void settingUserAndGetRefreshToken(User user, JwtToken jwtToken) {
        Optional<User> userOptional = userRepository.findByUserKey(user.getUserKey());
        if(userOptional.isEmpty()) {
            setUserSaltAndSave(user, jwtToken.getRefreshToken());
        } else  {
            updateUserSaltAndRefreshTokenIfNotExpired(userOptional.get(), jwtToken);
        }
    }

    private void setUserSaltAndSave(User user, String refreshToken) {
        user.setEmailWithEncryption();
        user.setSalt(refreshToken);
        userRepository.save(user);
    }



    private void updateUserSaltAndRefreshTokenIfNotExpired(User savedUser, JwtToken jwtToken) {
        if(!jwtToken.checkExpiredRefreshToken()) {
            String newRefreshToken = JwtMaker.makeRefreshToken();
            savedUser.setSalt(newRefreshToken);
            jwtToken.setRefreshToken(newRefreshToken);
            userRepository.save(savedUser);
        }
    }


}
