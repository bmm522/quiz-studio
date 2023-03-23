package com.jobseeckerstudio.bmm522.user.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jobseeckerstudio.bmm522.user.entity.User;
import com.jobseeckerstudio.bmm522.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.bmm522.user.jwt.properties.JwtProperties;

import java.util.Date;

public class JwtTokenFactory {

    private static JwtTokenFactory jwtTokenFactory = new JwtTokenFactory();

    public static JwtTokenFactory getJwtTokenFactory() {
        return jwtTokenFactory;
    }


    private JwtTokenFactory() {
    }

    public JwtToken getJwtToken(User user) {
        return JwtToken.builder()
                .jwtToken(JwtProperties.TOKEN_PREFIX + JWT.create()
                        .withSubject(user.getUserKey())
                        .withIssuer(JwtProperties.ISS)
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                        .withClaim("email", user.getEmail())
                        .sign(Algorithm.HMAC256(JwtProperties.SECRET)))
                .refreshToken(JwtProperties.REFRESH_PREFIX + JWT.create()
                        .withSubject(user.getUserKey())
                        .withIssuer(JwtProperties.ISS)
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REFRESHTOKEN_EXPIRATION_TIME))
                        .sign(Algorithm.HMAC256(JwtProperties.SECRET)))
                .build();
    }


}
