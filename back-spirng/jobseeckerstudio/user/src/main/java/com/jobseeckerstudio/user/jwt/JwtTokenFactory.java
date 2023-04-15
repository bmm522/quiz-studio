package com.jobseeckerstudio.user.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jobseeckerstudio.user.entity.User;
import com.jobseeckerstudio.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenFactory {


    public JwtToken create(User user) {
        return JwtToken.builder()
                .jwtToken(JwtProperties.TOKEN_PREFIX + JWT.create()
                        .withSubject(user.getUserKey())
                        .withIssuer(JwtProperties.ISS)
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                        .withClaim("userKey", user.getUserKey())
                        .sign(Algorithm.HMAC256(JwtProperties.SECRET)))
                .refreshToken(JwtProperties.REFRESH_PREFIX + JWT.create()
                        .withSubject(user.getUserKey())
                        .withIssuer(JwtProperties.ISS)
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REFRESHTOKEN_EXPIRATION_TIME))
                        .sign(Algorithm.HMAC256(JwtProperties.SECRET)))
                .build();
    }


}
