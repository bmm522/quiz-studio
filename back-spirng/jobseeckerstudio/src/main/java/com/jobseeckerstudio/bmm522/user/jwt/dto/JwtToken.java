package com.jobseeckerstudio.bmm522.user.jwt.dto;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.jobseeckerstudio.bmm522.user.jwt.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import lombok.Builder;
import lombok.Getter;

import java.util.Base64;

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
            Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes()))
                .parseClaimsJws(jwtToken.replace(JwtProperties.TOKEN_PREFIX, ""))
                .getBody();
        } catch (TokenExpiredException e) {
            throw new RuntimeException("토큰의 유효시간이 만료됐습니다.");
        }


    }
}
