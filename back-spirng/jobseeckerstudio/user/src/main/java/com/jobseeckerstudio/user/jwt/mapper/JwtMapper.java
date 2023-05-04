package com.jobseeckerstudio.user.jwt.mapper;

import com.jobseeckerstudio.user.exception.NotFoundTokenFromHeaderException;
import com.jobseeckerstudio.user.jwt.JwtToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class JwtMapper {

    // public static Optional<JwtToken> toJwtTokenOptional(HttpServletRequest request) {
    //     return Optional.ofNullable(JwtToken.builder()
    //         .jwtToken(request.getHeader("authorization"))
    //         .refreshToken(request.getHeader("refreshToken"))
    //         .build());
    // }

    public static JwtToken toJwtToken(HttpServletRequest request) {
        String jwtToken = getHeaderValue(request, "authorization");
        String refreshToken = getHeaderValue(request, "refreshToken");

        return  JwtToken.builder()
            .jwtToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
    }

    private static String getHeaderValue(HttpServletRequest request, String headerName) {
        String headerValue = request.getHeader(headerName);
        if (headerValue == null || headerValue.isEmpty()) {
            throw new NotFoundTokenFromHeaderException(String.format("헤더에서 %s 정보를 찾을 수 없습니다.", headerName));
        }
        return headerValue;
    }
}
