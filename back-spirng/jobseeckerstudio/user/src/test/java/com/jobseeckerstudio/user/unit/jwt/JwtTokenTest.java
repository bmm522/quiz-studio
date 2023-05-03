package com.jobseeckerstudio.user.unit.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.jwt.JwtToken;

import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@DisplayName("JwtToken 테스트")
@SpringBootTest
@TestPropertySource(locations = "classpath:application-dev.yml")
public class JwtTokenTest {

    private static final int EXPIRATION_TIME = 1000*60*60;

    private static final int REFRESHTOKEN_EXPIRATION_TIME = 14*24*6*10 *60000;
    private JwtToken jwtToken;

    private JwtToken invalidJwtToken;

    private JwtToken notHavePrefixJwtToken;

    @BeforeEach
    public void setup() throws ExpiredJwtException {
        User user = User.builder().userKey("testUser").build();
        jwtToken = JwtToken.builder()
            .jwtToken(JwtProperties.TOKEN_PREFIX+ JWT.create()
                .withSubject(user.getUserKey())
                .withIssuer(JwtProperties.ISS)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("userKey", user.getUserKey())
                .sign(Algorithm.HMAC256(JwtProperties.SECRET)))
            .refreshToken(JwtProperties.REFRESH_PREFIX +JWT.create()
                .withSubject("refreshToken")
                .withIssuer(JwtProperties.ISS)
                .withExpiresAt(new Date(System.currentTimeMillis() +  REFRESHTOKEN_EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(JwtProperties.SECRET)))
            .build();

        invalidJwtToken = JwtToken.builder()
            .jwtToken(JwtProperties.TOKEN_PREFIX + JWT.create()
                .withSubject(user.getUserKey())
                .withIssuer(JwtProperties.ISS)
                .withExpiresAt(new Date(System.currentTimeMillis()  - 1000000))
                .withClaim("userKey", user.getUserKey())
                .sign(Algorithm.HMAC256(JwtProperties.SECRET)))
            .refreshToken(JwtProperties.REFRESH_PREFIX +JWT.create()
                .withSubject("refreshToken")
                .withIssuer(JwtProperties.ISS)
                .withExpiresAt(new Date(System.currentTimeMillis()  - 1000000))
                .sign(Algorithm.HMAC256(JwtProperties.SECRET)))
            .build();

        notHavePrefixJwtToken = JwtToken.builder()
            .jwtToken(JWT.create()
                .withSubject(user.getUserKey())
                .withIssuer(JwtProperties.ISS)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("userKey", user.getUserKey())
                .sign(Algorithm.HMAC256(JwtProperties.SECRET)))
            .refreshToken(JWT.create()
                .withSubject("refreshToken")
                .withIssuer(JwtProperties.ISS)
                .withExpiresAt(new Date(System.currentTimeMillis() +  REFRESHTOKEN_EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(JwtProperties.SECRET)))
            .build();

    }

    // @Test
    // @DisplayName("accessToken validate 체크")
    // public void testCheckValidateJwtToken() {
    //     User user = User.
    //     assertFalse(jwtToken.checkValidateJwtToken());
    //
    //     jwtToken.setJwtToken(null);
    //     assertTrue(jwtToken.checkValidateJwtToken());
    //
    //     assertTrue(notHavePrefixJwtToken.checkValidateJwtToken());
    // }

    // @Test
    // @DisplayName("refreshToken validate 체크")
    // public void testCheckValidateRefreshToken() {
    //     assertFalse(jwtToken.checkValidateRefreshToken());
    //
    //     jwtToken.setRefreshToken(null);
    //     assertTrue(jwtToken.checkValidateRefreshToken());
    //
    //     assertTrue(notHavePrefixJwtToken.checkValidateRefreshToken());
    // }

    @Test
    @DisplayName("accessToken 유효시간 체크")
    public void testCheckExpiredToken() {
        assertTrue(jwtToken.checkExpiredToken());

        assertFalse(invalidJwtToken.checkExpiredToken());

    }

    @Test
    @DisplayName("refreshToken 유효시간 체크")
    public void testCheckExpiredRefreshToken() {

        assertTrue(jwtToken.checkExpiredRefreshToken());

        assertFalse(invalidJwtToken.checkExpiredToken());

    }
}
