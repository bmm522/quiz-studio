package com.jobseeckerstudio.user.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;

import java.util.Date;


public class JwtMaker {

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
        System.out.println(JwtProperties.SECRET);
        return JwtProperties.TOKEN_PREFIX+JWT.create()
            .withSubject(user.getUserKey())
            .withIssuer(JwtProperties.ISS)
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .withClaim("userKey", user.getUserKey())
            .sign(Algorithm.HMAC256(JwtProperties.SECRET));
    }

    public static String makeRefreshToken(){
        return JwtProperties.REFRESH_PREFIX + JWT.create()
            .withSubject("refreshToken")
            .withIssuer(JwtProperties.ISS)
            .withExpiresAt(new Date(System.currentTimeMillis() +  REFRESHTOKEN_EXPIRATION_TIME))
            .sign(Algorithm.HMAC256(JwtProperties.SECRET));
    }


}
