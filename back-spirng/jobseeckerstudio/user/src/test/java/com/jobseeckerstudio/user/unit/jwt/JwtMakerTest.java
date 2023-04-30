package com.jobseeckerstudio.user.unit.jwt;

import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JwtMaker 클래스")
class JwtMakerTest {

    @Value("${jwt.token_prefix}")
    private String TOKEN_PREFIX;

    @Value("${jwt.refresh_prefix}")
    private String REFRESH_PREFIX;
    @Value("${jwt.secret}")
    private String SECRET;
    @Test
    @DisplayName("create 메소드는 주어진 User 객체를 바탕으로 JwtToken 객체를 생성한다.")
    void createTest() {
        // Given
        User user = User.builder().userKey("test_user").build();

        // When
        JwtToken jwtToken = JwtMaker.create(user);

        // Then
        assertNotNull(jwtToken);
        assertEquals(TOKEN_PREFIX, jwtToken.getJwtToken().substring(0, 7));
        assertEquals(REFRESH_PREFIX, jwtToken.getRefreshToken().substring(0, 13));
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
        assertTrue(accessToken.startsWith(TOKEN_PREFIX));
        assertDoesNotThrow(() -> {
            Jws<Claims> claims = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(accessToken.substring(TOKEN_PREFIX.length()));
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
        assertTrue(refreshToken.startsWith(REFRESH_PREFIX));
        assertDoesNotThrow(() -> {
            Jws<Claims> claims = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(refreshToken.substring(REFRESH_PREFIX.length()));
            assertEquals("refreshToken", claims.getBody().getSubject());
            assertTrue(claims.getBody().getExpiration().after(new Date(System.currentTimeMillis())));
        });
    }
}
