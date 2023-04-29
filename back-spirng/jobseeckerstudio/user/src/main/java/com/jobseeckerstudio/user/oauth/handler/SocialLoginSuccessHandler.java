package com.jobseeckerstudio.user.oauth.handler;

import com.jobseeckerstudio.user.auth.principal.PrincipalDetails;
import com.jobseeckerstudio.user.domain.user.User;

import com.jobseeckerstudio.user.exception.ExpiredTokenException;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import com.jobseeckerstudio.user.oauth.cookie.CookieMaker;
import com.jobseeckerstudio.user.oauth.cookie.TokenCookie;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
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
        String refreshToken = settingUserAndGetRefreshToken(user, jwtToken.getRefreshToken());
//        addHeader(response, jwtToken);
        jwtToken.setRefreshToken(refreshToken);
        System.out.println("핸들러 : " + refreshToken);
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

    private String settingUserAndGetRefreshToken(User user, String refreshToekn) {
        Optional<User> userOptional = userRepository.findByUserKey(user.getUserKey());
        if(userOptional.isEmpty()) {
            user.setEmailWithEncryption();
            user.setSalt(refreshToekn);
            userRepository.save(user);
            return refreshToekn;
        } else  {
            User savedUser = userOptional.get();
            String salt = savedUser.getSalt();
            if(!checkExpiredRefreshToekn(salt)) {
                String newRefreshToken = JwtMaker.makeRefreshToken();
                user.setSalt(newRefreshToken);
                userRepository.save(user);
                return newRefreshToken;
            }
            return salt;
        }
    }

    private boolean checkExpiredRefreshToekn(String refreshToken) {
        try {
            Jws<Claims> claims = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes()))
                .parseClaimsJws(refreshToken.replace(JwtProperties.REFRESH_PREFIX, ""));

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
        } catch (JwtException e) {
            throw new ExpiredTokenException("유효하지 않은 토큰입니다.");
        }

        return true;
    }
}
