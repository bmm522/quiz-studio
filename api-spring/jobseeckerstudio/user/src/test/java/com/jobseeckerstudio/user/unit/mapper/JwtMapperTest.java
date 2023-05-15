package com.jobseeckerstudio.user.unit.mapper;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jobseeckerstudio.user.exception.NotFoundTokenFromHeaderException;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.jwt.mapper.JwtMapper;

public class JwtMapperTest {
    @Mock
    HttpServletRequest requestMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("헤더 정보가 존재할 때, JwtToken 객체 반환")
    void toJwtToken_whenHeadersArePresent_returnsJwtToken() {
        when(requestMock.getHeader("authorization")).thenReturn("JWT_TOKEN");
        when(requestMock.getHeader("refreshToken")).thenReturn("REFRESH_TOKEN");

        JwtToken jwtToken = JwtMapper.toJwtToken(requestMock);

        Assertions.assertEquals("JWT_TOKEN", jwtToken.getJwtToken());
        Assertions.assertEquals("REFRESH_TOKEN", jwtToken.getRefreshToken());
    }

    @Test
    @DisplayName("authorization 헤더가 누락되면, NotFoundTokenFromHeaderException 예외 발생")
    void toJwtToken_whenAuthorizationHeaderIsMissing_throwsException() {
        when(requestMock.getHeader("authorization")).thenReturn(null);
        when(requestMock.getHeader("refreshToken")).thenReturn("REFRESH_TOKEN");

        Assertions.assertThrows(NotFoundTokenFromHeaderException.class, () -> JwtMapper.toJwtToken(requestMock));
    }

    @Test
    @DisplayName("refreshToken 헤더가 누락되면, NotFoundTokenFromHeaderException 예외 발생")
    void toJwtToken_whenRefreshTokenHeaderIsMissing_throwsException() {
        when(requestMock.getHeader("authorization")).thenReturn("JWT_TOKEN");
        when(requestMock.getHeader("refreshToken")).thenReturn(null);

        Assertions.assertThrows(NotFoundTokenFromHeaderException.class, () -> JwtMapper.toJwtToken(requestMock));
    }
}
