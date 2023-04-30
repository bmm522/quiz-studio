package com.jobseeckerstudio.user.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.exception.ExpiredTokenException;
import com.jobseeckerstudio.user.exception.NotFoundUserException;
import com.jobseeckerstudio.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;


public class JwtMaker {

    @Value("${jwt.iss}")
    private static String ISS;

    @Value("${jwt.secret}")
    private static String SECRET;

    @Value("${jwt.token_prefix}")
    private static String TOKEN_PREFIX;

    @Value("${jwt.refresh_prefix}")
    private static String REFRESH_PREFIX;

    private static final int EXPIRATION_TIME = 1000*60*60;
    private static final int REFRESHTOKEN_EXPIRATION_TIME = 14*24*6*10 *60000;
    public static JwtToken create(User user) {
        String accessToken = makeAccessToken(user);
        String refreshToken = makeRefreshToken();

        return JwtToken.builder()
                .jwtToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public static String makeAccessToken(User user) {
        return TOKEN_PREFIX+JWT.create()
            .withSubject(user.getUserKey())
            .withIssuer(ISS)
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .withClaim("userKey", user.getUserKey())
            .sign(Algorithm.HMAC256(SECRET));
    }

    public static String makeRefreshToken(){
        return REFRESH_PREFIX + JWT.create()
            .withSubject("refreshToken")
            .withIssuer(ISS)
            .withExpiresAt(new Date(System.currentTimeMillis() +  REFRESHTOKEN_EXPIRATION_TIME))
            .sign(Algorithm.HMAC256(SECRET));
    }


}
