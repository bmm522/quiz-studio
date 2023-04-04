package com.jobseeckerstudio.bmm522.user.jwt.mapper;

import com.jobseeckerstudio.bmm522.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.bmm522.user.jwt.properties.JwtProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class JwtMapper {
    private static final JwtMapper jwtMapper = new JwtMapper();

    private JwtMapper() {
    }

    public static JwtMapper getJwtMapper() {
        return jwtMapper;
    }

    public Optional<JwtToken> toJwtTokenOptional(HttpServletRequest request) {
        return Optional.ofNullable(JwtToken.builder()
            .jwtToken(request.getHeader("authorization"))
            .refreshToken(request.getHeader("refreshToken"))
            .build());
    }

    public JwtToken toJwtToken(HttpServletRequest request) {
        return JwtToken.builder()
            .jwtToken(request.getHeader("authorization").replace(JwtProperties.TOKEN_PREFIX, ""))
            .refreshToken(request.getHeader("refreshToken").replace(JwtProperties.REFRESH_PREFIX, ""))
            .build();
    }
}
