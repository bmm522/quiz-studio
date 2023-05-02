package com.jobseeckerstudio.user.jwt.mapper;

import com.jobseeckerstudio.user.jwt.JwtToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class JwtMapper {

    public static Optional<JwtToken> toJwtTokenOptional(HttpServletRequest request) {
        return Optional.ofNullable(JwtToken.builder()
            .jwtToken(request.getHeader("authorization"))
            .refreshToken(request.getHeader("refreshToken"))
            .build());
    }

    public static JwtToken toJwtToken(HttpServletRequest request) {
        return JwtToken.builder()
            .jwtToken(request.getHeader("authorization"))
            .refreshToken(request.getHeader("refreshToken"))
            .build();
    }
}
