package com.jobseeckerstudio.bmm522.user.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtToken {

    private String jwtToken;
    private String refreshToken;

    @Builder
    public JwtToken(String jwtToken, String refreshToken){
        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
    }
}
