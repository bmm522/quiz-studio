package com.jobseeckerstudio.user.jwt;

import com.jobseeckerstudio.user.exception.ExpiredTokenException;

import com.jobseeckerstudio.user.exception.InvalidTokenException;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import io.jsonwebtoken.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Base64;
import java.util.Date;

@Getter
@Setter
public class JwtToken {

    private String jwtToken;
    private String refreshToken;

    @Builder
    public JwtToken(String jwtToken, String refreshToken){
        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
    }


    public void checkValidateJwtToken() {
        if(jwtToken == null ||jwtToken.isEmpty() || !jwtToken.startsWith(JwtProperties.TOKEN_PREFIX)) {
            throw  new InvalidTokenException("잘못된 토큰 정보입니다.");
        }
    }

    public void checkValidateRefreshToken() {
        if(refreshToken == null || refreshToken.isEmpty()||!refreshToken.startsWith(JwtProperties.REFRESH_PREFIX)){
            throw  new InvalidTokenException("잘못된 토큰 정보입니다.");
        }
    }

    public boolean checkExpiredToken() {
        try {
            Jws<Claims> claims = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes()))
                .parseClaimsJws(jwtToken.replace(JwtProperties.TOKEN_PREFIX, ""));

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
        } catch (JwtException e) {
            return false;
        }
        return true;
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
            return false;
        }

        return true;
    }

}

