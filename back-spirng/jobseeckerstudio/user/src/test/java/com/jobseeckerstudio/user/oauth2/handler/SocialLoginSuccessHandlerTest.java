package com.jobseeckerstudio.user.oauth2.handler;

import com.jobseeckerstudio.user.auth.principal.PrincipalDetails;
import com.jobseeckerstudio.user.entity.User;
import com.jobseeckerstudio.user.jwt.JwtTokenFactory;
import com.jobseeckerstudio.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import com.jobseeckerstudio.user.oauth.cookie.CookieFactory;
import com.jobseeckerstudio.user.oauth.handler.SocialLoginSuccessHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SocialLoginSuccessHandlerTest {

    @Mock
    private JwtTokenFactory jwtTokenFactory;

    @Mock
    private PrincipalDetails principalDetails;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Cookie cookie;

    @Mock
    private CookieFactory cookieFactory;

    @Mock
    private Authentication authentication;

    private SocialLoginSuccessHandler successHandler;

    @BeforeEach
    void setUp() {
        successHandler = new SocialLoginSuccessHandler(jwtTokenFactory);
    }

    @Test
    void onAuthenticationSuccessTest() throws Exception {
        // given
        JwtToken jwtToken = JwtToken.builder()
            .jwtToken("testToken")
            .refreshToken("testRefreshToken")
            .build();

        when(authentication.getPrincipal()).thenReturn(principalDetails);
        when(principalDetails.getUser()).thenReturn(new User());
        when(jwtTokenFactory.create(any(User.class))).thenReturn(jwtToken);

        // when
        successHandler.onAuthenticationSuccess(null, response, authentication);

        // then
        verify(jwtTokenFactory).create(any(User.class));
        verify(response).addHeader(eq(JwtProperties.HEADER_JWT_STRING), eq("testToken"));
        verify(response).addHeader(eq(JwtProperties.HEADER_REFRESHTOKEN_STRING), eq("testRefreshToken"));

    }
}
