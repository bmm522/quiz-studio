package com.jobseeckerstudio.user.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.exception.ExpiredTokenException;
import com.jobseeckerstudio.user.exception.NotFoundUserException;
import com.jobseeckerstudio.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;


public class JwtMaker {

    public static JwtToken create(User user) {
        String accessToken = makeAccessToken(user);
        String refreshToken = makeRefreshToken();

        return JwtToken.builder()
                .jwtToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public static String makeAccessToken(User user) {
        return JwtProperties.TOKEN_PREFIX+JWT.create()
            .withSubject(user.getUserKey())
            .withIssuer(JwtProperties.ISS)
            .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
            .withClaim("userKey", user.getUserKey())
            .sign(Algorithm.HMAC256(JwtProperties.SECRET));
    }

    public static String makeRefreshToken(){
        return JwtProperties.REFRESH_PREFIX + JWT.create()
            .withSubject("refreshToken")
            .withIssuer(JwtProperties.ISS)
            .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REFRESHTOKEN_EXPIRATION_TIME))
            .sign(Algorithm.HMAC256(JwtProperties.SECRET));
    }


}
