package com.jobseeckerstudio.user.unit.oauth;

import com.jobseeckerstudio.user.auth.principal.PrincipalDetails;
import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.oauth.handler.SocialLoginSuccessHandler;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-dev.yml")
public class SocialLoginSuccessHandlerTest {

    @InjectMocks
    @Spy
    private SocialLoginSuccessHandler socialLoginSuccessHandler;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Authentication authentication;

    @Mock
    private PrincipalDetails principalDetails;


//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    @DisplayName("새로운 로그인 시에 핸들러")
    public void onAuthenticationSuccessTestWhenNewUser() throws Exception {
        User user = User.builder().userKey("test").email("test@test.com").build();
        // Arrange
        when(authentication.getPrincipal()).thenReturn(principalDetails);
        when(principalDetails.getUser()).thenReturn(user);
        when(userRepository.findByUserKey(user.getUserKey())).thenReturn(Optional.empty());
        doNothing().when(response).sendRedirect(anyString());

        // Act
        socialLoginSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        // Assert
        verify(userRepository, times(1)).findByUserKey(user.getUserKey()); // 1. findByUserKey() 메서드가 호출되는지 확인
        verify(userRepository, times(1)).save(user); // 2. save() 메서드가 호출되는지 확인
        verify(socialLoginSuccessHandler, times(1)).setUserSaltAndSave(any(), any());
        verify(response, times(2)).addCookie(any()); // 3. 쿠키가 추가되는지 확인
        verify(response, times(1)).sendRedirect(anyString()); // 4. sendRedirect() 메서드가 호출되는지 확인
    }
    @Test
    @DisplayName("기존 유저 로그인 시에 핸들러")
    public void onAuthenticationSuccessTestWhenExistUser() throws Exception {
        User user = User.builder().userKey("test").email("test@test.com").build();
        // Arrange
        when(authentication.getPrincipal()).thenReturn(principalDetails);
        when(principalDetails.getUser()).thenReturn(user);
        when(userRepository.findByUserKey(user.getUserKey())).thenReturn(Optional.of(user));
        doNothing().when(response).sendRedirect(anyString());

        // Act
        socialLoginSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        // Assert
        verify(userRepository, times(1)).findByUserKey(user.getUserKey()); // 1. findByUserKey() 메서드가 호출되는지 확인
        verify(socialLoginSuccessHandler, times(1)).updateUserSaltAndRefreshTokenIfNotExpired(any(), any());
        verify(response, times(2)).addCookie(any()); // 3. 쿠키가 추가되는지 확인
        verify(response, times(1)).sendRedirect(anyString()); // 4. sendRedirect() 메서드가 호출되는지 확인
    }
}
