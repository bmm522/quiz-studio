//package com.jobseeckerstudio.user.oauth2.handler;
//
//import com.jobseeckerstudio.user.auth.principal.PrincipalDetails;
//import com.jobseeckerstudio.user.domain.user.User;
//import com.jobseeckerstudio.user.jwt.JwtMaker;
//import com.jobseeckerstudio.user.jwt.dto.JwtToken;
//import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
//import com.jobseeckerstudio.user.oauth.cookie.CookieMaker;
//import com.jobseeckerstudio.user.oauth.handler.SocialLoginSuccessHandler;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.core.Authentication;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class SocialLoginSuccessHandlerTest {
//
//    @Mock
//    private JwtMaker jwtMaker;
//
//    @Mock
//    private PrincipalDetails principalDetails;
//
//    @Mock
//    private HttpServletResponse response;
//
//    @Mock
//    private Cookie cookie;
//
//    @Mock
//    private CookieMaker cookieMaker;
//
//    @Mock
//    private Authentication authentication;
//
//    private SocialLoginSuccessHandler successHandler;
//
//    @BeforeEach
//    void setUp() {
//        successHandler = new SocialLoginSuccessHandler();
//    }
//
//    @Test
//    void onAuthenticationSuccessTest() throws Exception {
//        // given
//        JwtToken jwtToken = JwtToken.builder()
//            .jwtToken("testToken")
//            .refreshToken("testRefreshToken")
//            .build();
//
//        when(authentication.getPrincipal()).thenReturn(principalDetails);
//        when(principalDetails.getUser()).thenReturn(new User());
//        when(JwtMaker.create(any(User.class))).thenReturn(jwtToken);
//
//        // when
//        successHandler.onAuthenticationSuccess(null, response, authentication);
//
//        // then
//        verify(jwtMaker).create(any(User.class));
//        verify(response).addHeader(eq(JwtProperties.HEADER_JWT_STRING), eq("testToken"));
//        verify(response).addHeader(eq(JwtProperties.HEADER_REFRESHTOKEN_STRING), eq("testRefreshToken"));
//
//    }
//}
