package com.jobseeckerstudio.user.jwt.dto;

import com.jobseeckerstudio.user.exception.ExpiredTokenException;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import io.jsonwebtoken.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Base64;
import java.util.Date;

@Getter
public class JwtToken {

    private String jwtToken;
    private String refreshToken;

    @Builder
    public JwtToken(String jwtToken, String refreshToken){
        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
    }

    public boolean checkValidateJwtToken() {
        return jwtToken == null || !jwtToken.startsWith(JwtProperties.TOKEN_PREFIX);
    }

    public boolean checkValidateRefreshToken() {
        return refreshToken == null || !refreshToken.startsWith(JwtProperties.REFRESH_PREFIX);
    }

    public void checkExpiredToken() {
        try {
            Jws<Claims> claims = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes()))
                .parseClaimsJws(jwtToken.replace(JwtProperties.TOKEN_PREFIX, ""));

            if (claims.getBody().getExpiration().before(new Date())) {
                throw new ExpiredTokenException("토큰의 유효시간이 만료됐습니다.");
            }
        } catch (JwtException e) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }
    }

    public boolean checkExpiredRefreshToken() {
        try {
            Jws<Claims> claims = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes()))
                .parseClaimsJws(refreshToken.replace(JwtProperties.REFRESH_PREFIX, ""));

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
        } catch (JwtException e) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }

        return true;
    }

}

