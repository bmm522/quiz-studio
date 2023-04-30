package com.jobseeckerstudio.user.jwt.dto;

import com.jobseeckerstudio.user.exception.ExpiredTokenException;

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


    public boolean checkValidateJwtToken() {
        return jwtToken == null || !jwtToken.startsWith(JwtProperties.TOKEN_PREFIX);
    }

    public boolean checkValidateRefreshToken() {
        return refreshToken == null || !refreshToken.startsWith(JwtProperties.REFRESH_PREFIX);
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

