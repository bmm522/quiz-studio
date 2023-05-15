package com.jobseeckerstudio.user.unit.jwt;

import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JwtMaker 클래스")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class JwtMakerTest {


    @Test
    @DisplayName("create 메소드는 주어진 User 객체를 바탕으로 JwtToken 객체를 생성한다.")
    void createTest() {
        // Given
        User user = User.builder().userKey("test_user").build();

        // When
        JwtToken jwtToken = JwtMaker.create(user);

        // Then
        assertNotNull(jwtToken);
        assertEquals(JwtProperties.TOKEN_PREFIX, jwtToken.getJwtToken().substring(0, 7));
        assertEquals(JwtProperties.REFRESH_PREFIX, jwtToken.getRefreshToken().substring(0, 13));
    }

    @Test
    @DisplayName("makeAccessToken 메소드는 주어진 User 객체를 바탕으로 액세스 토큰을 생성한다.")
    void makeAccessTokenTest() {
        // Given
        User user = User.builder().userKey("test_user").build();

        // When
        String accessToken = JwtMaker.makeAccessToken(user);

        // Then
        assertNotNull(accessToken);
        assertTrue(accessToken.startsWith(JwtProperties.TOKEN_PREFIX));
        assertDoesNotThrow(() -> {
            Jws<Claims> claims = Jwts.parser()
                .setSigningKey(JwtProperties.SECRET.getBytes())
                .parseClaimsJws(accessToken.substring(JwtProperties.TOKEN_PREFIX.length()));
            assertEquals(user.getUserKey(), claims.getBody().getSubject());
            assertEquals(user.getUserKey(), claims.getBody().get("userKey"));
        });
    }

    @Test
    @DisplayName("makeRefreshToken 메소드는 리프레시 토큰을 생성한다.")
    void makeRefreshTokenTest() {
        // When
        String refreshToken = JwtMaker.makeRefreshToken();

        // Then
        assertNotNull(refreshToken);
        assertTrue(refreshToken.startsWith(JwtProperties.REFRESH_PREFIX));
        assertDoesNotThrow(() -> {
            Jws<Claims> claims = Jwts.parser()
                .setSigningKey(JwtProperties.SECRET.getBytes())
                .parseClaimsJws(refreshToken.substring(JwtProperties.REFRESH_PREFIX.length()));
            assertEquals("refreshToken", claims.getBody().getSubject());
            assertTrue(claims.getBody().getExpiration().after(new Date(System.currentTimeMillis())));
        });
    }
}
